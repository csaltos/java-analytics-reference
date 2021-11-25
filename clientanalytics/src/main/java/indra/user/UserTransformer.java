package indra.user;

import org.bson.Document;
import org.bson.types.ObjectId;

public class UserTransformer {

	public static UserData toUserData(Document doc) {
		return new UserData(doc.getObjectId("_id").toHexString(), doc.getString("name"));
	}
	
	public static Document toDocument(UserData userData) {
		return new Document()
				.append("_id", new ObjectId(userData.getId()))
				.append("name", userData.getName());
	}
}
