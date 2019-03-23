package io.holunda.mesooncome.task.event;

import lombok.Value;

@Value
public class DelegateTaskEvent {
    private final String id;
    private final String userId;
}
