package io.holunda.mesooncome.controller;

import io.holunda.mesooncome.config.ElasticSearchProperties;
import io.holunda.mesooncome.model.es.Hit;
import io.holunda.mesooncome.model.es.RequestBody;
import io.holunda.mesooncome.model.es.SearchResult;
import io.holunda.mesooncome.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
public class SearchController {

    @Autowired
    private ElasticSearchProperties elasticSearchProperties;

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/api/task")
    @CrossOrigin("http://localhost:3000")
    SearchResult task() {
        String url = elasticSearchProperties.getUrl() + "/_search";
        log.info("Url=" + url);
        HttpEntity<RequestBody> request = new HttpEntity<>(searchService.getRequestBody());
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(url, request, SearchResult.class);
    }

}
