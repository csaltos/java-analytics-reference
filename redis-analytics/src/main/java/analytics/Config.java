package analytics;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

@Configuration
public class Config {

	@Bean
	Jedis getJedis() {
		return new Jedis(new HostAndPort("esmeralda.csaltos.com", 6379));
	}
}
