package io.holunda.mesooncome.task.event;

import lombok.Value;

@Value
public class CompleteTaskEvent {
    private final String id;
}
