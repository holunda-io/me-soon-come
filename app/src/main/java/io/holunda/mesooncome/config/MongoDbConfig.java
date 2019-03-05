package io.holunda.mesooncome.config;

import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Value;

import com.mongodb.MongoClient;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EntityScan("io.holunda.mesooncome.model.db")
@EnableMongoRepositories(basePackages="io.holunda.mesooncome.database")
public class MongoDbConfig extends AbstractMongoConfiguration {

    @Value("${mongodb.port:27017}")
    private String mongodbPort;
    @Value("${mongodb.host:localhost}")
    private String mongodbHost;
    @Value("${mongodb.name:meesooncome}")
    private String mongodbName;

    @Override
    protected String getDatabaseName() {
        return mongodbName;
    }

    @Override
    public MongoMappingContext mongoMappingContext()
            throws ClassNotFoundException {
        // TODO Auto-generated method stub
        return super.mongoMappingContext();
    }

    @Override
    @Bean
    public MongoClient mongoClient()  {
        return new MongoClient( mongodbHost+":"+mongodbPort );
    }

}