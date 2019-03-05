package io.holunda.mesooncome.config;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConf {

    @Bean
    public ElasticSearchProperties elasticSearchProperties(@Value("${elasticsearch.host:localhost}")
                                                                   String host,
                                                           @Value("${elasticsearch.port:9200}")
                                                                   String port) {
        return ElasticSearchProperties.builder()
                .host(host)
                .port(port)
                .build();
    }

}

