package indra.user;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

@RestController
public class UserApiController {

	@Autowired
	private MongoCollection<Document> collection;

	@GetMapping("/user/{id}")
	public UserData readUser(@PathVariable String id) {
		final Bson filter = Filters.eq("_id", new ObjectId(id));
		final UserData result = collection.find(filter)
				.map(UserTransformer::toUserData)
				.first();
		return result;
	}

	@PutMapping("/user")
	public UserData updateUser(@RequestBody UserData user) {
		final Bson filter = Filters.eq("_id", new ObjectId(user.getId()));
		collection.updateOne(filter, new Document("$set", UserTransformer.toDocument(user)));
		return user;
	}
	
	@PostMapping("/user")
	public UserData createUser(@RequestBody UserData user) {
		if (user.getId() == null) {
			final ObjectId id = new ObjectId();
			user.setId(id.toHexString());
		}
		final Document userDoc = new Document()
				.append("_id", new ObjectId(user.getId()))
				.append("name", user.getName());
		collection.insertOne(userDoc);
		return user;
	}

	@GetMapping("/user")
	public Stream<UserData> listUsers() {
		return StreamSupport.stream(collection.find().spliterator(), false)
				.map(UserTransformer::toUserData);
	}

	@DeleteMapping("/user/{id}")
	public boolean deleteUser(@PathVariable String id) {
		final Bson filter = Filters.eq("_id", new ObjectId(id));
		DeleteResult result = collection.deleteOne(filter);
		return result.getDeletedCount() > 0;
	}

}
