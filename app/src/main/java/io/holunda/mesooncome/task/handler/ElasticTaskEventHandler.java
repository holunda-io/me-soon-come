package io.holunda.mesooncome.task.handler;

import io.holunda.mesooncome.config.ElasticSearchProperties;
import io.holunda.mesooncome.task.event.CreateTaskEvent;
import io.holunda.mesooncome.task.event.UpdateTaskEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@AllArgsConstructor
public class ElasticTaskEventHandler {
    private final ElasticSearchProperties elasticSearchProperties;
    private final CommandGateway commandGateway;

    @EventHandler
    public void handle(CreateTaskEvent event) {
        String url = elasticSearchProperties.getUrl() + "task/_doc?pretty";
        log.info("Url=" + url);

        HttpEntity<Object> request = new HttpEntity<>(event.getTaskAsObject());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TaskAddedResult> response = restTemplate.postForEntity(url, request, TaskAddedResult.class);

    }

    @EventHandler
    public void handle(UpdateTaskEvent event) {
        //TODO
    }
}
