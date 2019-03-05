package io.holunda.mesooncome.controller;

import io.holunda.mesooncome.config.ElasticSearchProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class ImportController {

    @Autowired
    private ElasticSearchProperties elasticSearchProperties;

    @RequestMapping(value = "/import/task", method = RequestMethod.POST)
    Object addTask(@RequestBody Object task) {
        String url = elasticSearchProperties.getUrl() + "task/_doc?pretty";
        log.info("Url=" + url);

        HttpEntity<Object> request = new HttpEntity<>(task);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForLocation(url, request);
    }

}
