package indra.config;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.flapdoodle.embed.mongo.MongodProcess;

@Component
public class CleanMongoDb {

	@Autowired
	private MongodProcess mongoDbEmbeddedServer;
	
    @PreDestroy
    public void onDestroy() throws Exception {
    	mongoDbEmbeddedServer.stop();
    }
}
