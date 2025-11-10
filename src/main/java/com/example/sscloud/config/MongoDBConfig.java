package com.example.sscloud.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoDBConfig {
    @Value("${spring.data.mongodb.url}")
    private String mongoDbUrl;

    @Value("${spring.data.mongodb.database}")
    private String mongoDbName;

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        SimpleMongoClientDatabaseFactory factory =  new SimpleMongoClientDatabaseFactory(createMongoClient(),mongoDbName);
        return new MongoTemplate(factory);

    }
    @Bean
    public MongoClient createMongoClient() {
        MongoClientSettings.Builder builder = MongoClientSettings.builder();
        applyConnectionString(builder);
        return MongoClients.create(builder.build());
    }

    private void applyConnectionString(MongoClientSettings.Builder builder) {
        ConnectionString connectionString = new ConnectionString(mongoDbUrl);
        builder.applyConnectionString(connectionString);
    }




}
