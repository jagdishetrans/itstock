package com.etrans.itstock.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;

@Configuration
public class MongoConfig {

	@Bean
	public MongoTemplate mongoTemplate() throws IOException {
		EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
		mongo.setBindIp("localhost");
		MongoClient mongoClient = mongo.getObject();
		MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, "embedded_db");
		return mongoTemplate;
	}

}
