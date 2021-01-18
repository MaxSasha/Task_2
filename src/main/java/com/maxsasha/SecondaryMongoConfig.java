package com.maxsasha;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = "com.maxsasha.secondaryDb.db.repository", mongoTemplateRef = "secondaryMongoTemplate")
@ConfigurationProperties(prefix = "secondary.mongodb")
public class SecondaryMongoConfig {

    @Value("${secondary.mongodb.database}")
    private String dbName;

    @Bean(name = "secondaryMongoTemplate")
    public MongoTemplate getMongoTemplate() throws Exception {
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(MongoClients.create(), dbName));
    }
}