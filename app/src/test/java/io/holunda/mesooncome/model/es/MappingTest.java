package io.holunda.mesooncome.model.es;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

public class MappingTest {
    @Test
    public void shoulGetTwoPathProperties() {

        Map<String, Property> props = new HashMap();
        props.put("dataEntries", Property.builder()
                .properties(Collections.singletonMap("entryId", Property.builder()
                        .properties(Collections.singletonMap("test", Property.builder().build()))
                        .build()))
                .build());

        Map<String, Property> taskProps = new HashMap();
        taskProps.put("assignee", Property.builder().build());
        taskProps.put("dueDate", Property.builder().build());

        props.put("task", Property.builder()
                .properties(taskProps)
                .build());
        Mapping mapping = Mapping.builder()
                .properties(props)
                .build();

        List<String> mappingPropertiesAsPath = mapping.getPropertiesAsPath();

        assertThat(mappingPropertiesAsPath,
                hasItems("task.assignee", "task.dueDate", "dataEntries.entryId.test"));

    }
}