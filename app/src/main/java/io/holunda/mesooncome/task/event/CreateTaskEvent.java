package io.holunda.mesooncome.task.event;

import io.holunda.mesooncome.api.Task;
import lombok.Value;

@Value
public class CreateTaskEvent implements TaskEvent{
    private final String id;
    private final Object taskAsObject;
    private final Task task;
}
