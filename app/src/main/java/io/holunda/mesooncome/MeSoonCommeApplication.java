package io.holunda.mesooncome;

import io.holunda.mesooncome.model.es.Hit;
import io.holunda.mesooncome.model.es.SearchResult;
import io.holunda.mesooncome.model.es.search.Order;
import io.holunda.mesooncome.model.es.search.RequestBody;
import io.holunda.mesooncome.model.es.search.Sort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
        String url = "http://" + elasticsearchHost + ":" + elasticsearchPort + "/_search";
        log.info("ElasticSearchUrl=" + url);

        HttpEntity<RequestBody> request = new HttpEntity<>(createRequest());
        SearchResult searchResult = restTemplate.postForObject(url, request, SearchResult.class);
        return searchResult.getHits().getHits();
    }

    private RequestBody createRequest() {
        List<Sort> sorts = new ArrayList<>();

        sorts.add(Sort.builder()
                .order("task.dueDate", Order.builder()
                        .order("asc")
                        .build())
                .build());
        sorts.add(Sort.builder()
                .order("task.createTime", Order.builder()
                        .order("desc")
                        .build())
                .build());

        return RequestBody.builder()
                .sort(sorts)
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(MeSoonCommeApplication.class, args);
    }

}
