package io.holunda.mesooncome.task.event;

import lombok.Value;

@Value
public class CreateTaskEvent {
    private final String id;
    private final Object task;
}
