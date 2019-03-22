package io.holunda.mesooncome.task.event;

import lombok.Value;

@Value
public class CreateTaskEvent {
    private String id;
    private final Object task;
}
