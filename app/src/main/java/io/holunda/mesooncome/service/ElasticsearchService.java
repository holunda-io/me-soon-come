package io.holunda.mesooncome.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.holunda.mesooncome.config.ElasticSearchProperties;
import io.holunda.mesooncome.model.es.Index;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Slf4j
@Component
@AllArgsConstructor
public class ElasticsearchService {

    private final ElasticSearchProperties elasticSearchProperties;
    private final ObjectMapper objectMapper;

    public Index getIndex(String index) {

        String url = elasticSearchProperties.getUrl() + "/" + index;
        log.info("Url=" + url);
        RestTemplate restTemplate = new RestTemplate();

        return objectMapper.convertValue(restTemplate.getForObject(url, Map.class).get(index), Index.class);

    }
}
