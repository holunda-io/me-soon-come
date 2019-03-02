package io.holunda.mesooncome;

import io.holunda.mesooncome.model.es.Hit;
import io.holunda.mesooncome.model.es.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@SpringBootApplication
@Slf4j
public class
MeSoonCommeApplication {

    @Value("${elasticsearch.host:localhost}")
    private String elasticsearchHost;
    @Value("${elasticsearch.port:9200}")
    private String elasticsearchPort;

    @RequestMapping(value = "/api/")
    List<Hit> home() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://" + elasticsearchHost + ":" + elasticsearchPort + "/_all/_search?q=*";
        log.info("ElasticSearchUrl=" + url);
        SearchResult searchResult = restTemplate.getForObject(url, SearchResult.class);
        return searchResult.getHits().getHits();
    }

    public static void main(String[] args) {
        SpringApplication.run(MeSoonCommeApplication.class, args);
    }

}
