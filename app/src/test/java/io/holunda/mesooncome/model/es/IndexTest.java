package io.holunda.mesooncome.model.es;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Slf4j
public class IndexTest {
    @Test
    public void jsonShouldHaveTwoSorts() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Index> indizes = new HashMap<>();
        Map<String, Mapping> mappings = new HashMap<>();
        Map<String, Property> taskProps = new HashMap<>();
        taskProps.put("description", Property.builder()
                .type("text")
                .fields(Collections
                        .singletonMap("keyword", Field.builder()
                                .ignore_above(256L)
                                .type("keyword")
                                .build()))
                .build());

        taskProps.put("dueDate", Property.builder()
                .type("date")
                .build());

        taskProps.put("payload", Property.builder()
                .properties(Collections.singletonMap("request", Property.builder()
                        .type("text")
                        .fields(Collections.singletonMap("keyword", Field.builder()
                                .type("keyword")
                                .ignore_above(256L)
                                .build()))
                        .build()))
                .build());

        mappings.put("_doc", Mapping.builder()
                .properties(Collections.singletonMap("task", Property.builder()
                        .properties(taskProps)
                        .build()))
                .build());
        indizes.put("task", Index.builder()
                .mappings(mappings)
                .build());


        String json = new Scanner(IndexTest
                .class
                .getResourceAsStream("/es/index_with_mapping.json"), "UTF-8").useDelimiter("\\A").next();

        TypeReference<HashMap<String, Index>> typeRef
                = new TypeReference<HashMap<String, Index>>() {
        };

        Map<String, Index> indizesFromFile = mapper.readValue(json, typeRef);
        assertThat(indizes, is(indizesFromFile));
    }



}