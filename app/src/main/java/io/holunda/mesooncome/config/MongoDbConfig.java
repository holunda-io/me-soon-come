package io.holunda.mesooncome.config;

import com.mongodb.MongoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Slf4j
@Configuration
@EntityScan("io.holunda.mesooncome.model.db")
@EnableMongoRepositories(basePackages = "io.holunda.mesooncome.database")
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
    public MongoClient mongoClient() {
        log.info("MongoClient configured with mongodbHost: {}, mongodbPort: {}, mongodbName: {}", mongodbHost, mongodbPort, mongodbName);
        return new MongoClient(mongodbHost + ":" + mongodbPort);
    }

}