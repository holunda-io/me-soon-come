package io.holunda.mesooncome.task.event;

import io.holunda.mesooncome.task.handler.TaskAddedResult;
import lombok.Value;

@Value
public class RegisterTaskCreatedResultEvent {
    private String id;
    private final TaskAddedResult task;
}
