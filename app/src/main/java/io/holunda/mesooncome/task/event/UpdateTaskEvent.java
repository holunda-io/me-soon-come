package io.holunda.mesooncome.task.event;

import lombok.Value;

@Value
public class UpdateTaskEvent {
    private final String id;
    private final Object task;
}
