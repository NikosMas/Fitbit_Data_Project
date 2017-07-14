package com.grad.config;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.web.client.RestTemplate;

import com.mongodb.MongoClient;

/**
 * beans configuration associated with required beans
 * 
 * @author nikos_mas, alex_kak
 */

@Configuration
public class BeansConfiguration {

	@Autowired
	private MongoProperties mongoProp;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(new MongoClient(mongoProp.getHost(), mongoProp.getPort()), mongoProp.getDbname());
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}

}
