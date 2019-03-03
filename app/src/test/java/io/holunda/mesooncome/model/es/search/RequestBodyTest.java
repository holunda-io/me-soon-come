package io.holunda.mesooncome.model.es.search;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Slf4j
public class RequestBodyTest {
    @Test
    public void jsonShouldHaveTwoSorts() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //mapper.enableDefaultTyping();

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

        RequestBody requestBody = RequestBody.builder()
                .sort(sorts)
                .build();
        log.info(mapper.writeValueAsString(requestBody));

        String json = new Scanner(RequestBodyTest.class.getResourceAsStream("/sort.json"), "UTF-8").useDelimiter("\\A").next();

        TypeReference<RequestBody> typeRef
                = new TypeReference<RequestBody>() {
        };

        RequestBody requestBodyFromFile = mapper.readValue(json, typeRef);
        assertThat(requestBody, is(requestBodyFromFile));
    }

}