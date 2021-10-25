package analytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import redis.clients.jedis.Jedis;

@SpringBootApplication
public class RedisAnalyticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisAnalyticsApplication.class, args);
	}

	@Autowired
	private Jedis jedis;
	
	@Bean
	public CommandLineRunner callRedis() {
		return args -> {
				jedis.set("prueba", "ok");
				System.out.println(jedis.get("prueba"));
				jedis.incr("visitas");
				System.out.println(jedis.get("visitas"));
		};
	}
}
