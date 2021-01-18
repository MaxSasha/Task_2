package com.maxsasha;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = "com.maxsasha.primaryDb.db.repository", mongoTemplateRef = "primaryMongoTemplate")
@ConfigurationProperties(prefix = "primary.mongodb")
public class PrimaryMongoConfig {

    @Value("${primary.mongodb.database}")
    private String dbName;

    @Primary
    @Bean(name = "primaryMongoTemplate")
    public MongoTemplate getMongoTemplate() throws Exception {
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(MongoClients.create(), dbName));
    }
}