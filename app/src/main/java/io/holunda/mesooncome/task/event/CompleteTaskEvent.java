package io.holunda.mesooncome.task.event;

import io.holunda.mesooncome.api.Task;
import lombok.Value;

@Value
public class CompleteTaskEvent implements TaskEvent{
    private final String id;
    private final Task task;

}
