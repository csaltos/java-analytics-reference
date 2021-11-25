package indra.config;

import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

@Configuration
public class Config {
	
	@Bean
	public MongodProcess mongoDbEmbeddedServer() throws Exception {
		int mongoDbPort = Network.getFreeServerPort();
		MongodStarter starter = MongodStarter.getDefaultInstance();

		MongodConfig mongodConfig = MongodConfig.builder()
		    .version(Version.Main.PRODUCTION)
		    .net(new Net(mongoDbPort, Network.localhostIsIPv6()))
		    .build();

		MongodExecutable mongodExecutable = null;
		mongodExecutable = starter.prepare(mongodConfig);
		MongodProcess mongod = mongodExecutable.start();
		return mongod;
	}

	@Bean
	public MongoClient getMongoDbClient(MongodProcess mongoDbServer) {
		int monboDbPort = mongoDbServer.getConfig().net().getPort();
		MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:" + monboDbPort));
		return client;
	}

	@Bean
	public MongoDatabase getMongoDbDatabase(MongoClient client) {
		MongoDatabase db = client.getDatabase("test");
		return db;
	}
	
	@Bean
	public MongoCollection<Document> getMongoDbCollection(MongoDatabase db) {
		MongoCollection<Document> collection = db.getCollection("test");
		return collection;
	}

}
