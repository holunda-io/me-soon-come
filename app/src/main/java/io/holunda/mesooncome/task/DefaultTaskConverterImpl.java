package io.holunda.mesooncome.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.holunda.mesooncome.api.Task;
import io.holunda.mesooncome.api.TaskConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class DefaultTaskConverterImpl implements TaskConverter {

    private final ObjectMapper objectMapper;

    public Task convertObjectToTask(Object taskAsObject) {
        taskAsObject = objectMapper.convertValue(taskAsObject, Map.class).get("task");
        return objectMapper.convertValue(taskAsObject, DefaultTaskImpl.class);
    }
}
