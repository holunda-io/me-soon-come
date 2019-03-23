package io.holunda.mesooncome.task.event;

import lombok.Value;

@Value
public class ClaimTaskEvent {
    private final String id;
    private final String userId;
}
