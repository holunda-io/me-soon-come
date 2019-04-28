package io.holunda.mesooncome.config;

import com.github.mongobee.Mongobee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MongoBeeConfig {

    @Value("${mongodb.port:27017}")
    private String mongodbPort;
    @Value("${mongodb.host:localhost}")
    private String mongodbHost;
    @Value("${mongodb.name:meesooncome}")
    private String mongodbName;

    @Bean
    public Mongobee mongobee() {
        log.info("Mongobee configured with mongodbHost: {}, mongodbPort: {}, mongodbName: {}", mongodbHost, mongodbPort, mongodbName);

        Mongobee runner = new Mongobee("mongodb://" + mongodbHost + ":" + mongodbPort + "/" + mongodbName);
        runner.setDbName(mongodbName);         // host must be set if not set in URI
        runner.setChangeLogsScanPackage(
                "io.holunda.mesooncome.database.init"); // the package to be scanned for changesets

        return runner;
    }
}
