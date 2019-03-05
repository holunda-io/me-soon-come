package io.holunda.mesooncome.model.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.holunda.mesooncome.model.es.RequestBodyTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Slf4j
public class RequestBodyEntityTest {
    @Test
    public void jsonShouldHaveTwoSorts() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //mapper.enableDefaultTyping();

        List<SortEntity> sorts = new ArrayList<>();

        sorts.add(SortEntity.builder()
                .path("task.dueDate")
                .order("asc")
                .build());
        sorts.add(SortEntity.builder()
                .path("task.createTime")
                .order("desc")
                .build());

        RequestBodyEntity requestBody = RequestBodyEntity.builder()
                .sorts(sorts)
                .build();
        log.info(mapper.writeValueAsString(requestBody));

        String json = new Scanner(RequestBodyTest
                .class
                .getResourceAsStream("/db/request_body.json"), "UTF-8").useDelimiter("\\A").next();


        RequestBodyEntity requestBodyFromFile = mapper.readValue(json, RequestBodyEntity.class);
        assertThat(requestBody, is(requestBodyFromFile));
    }
}