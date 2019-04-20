package io.holunda.mesooncome.task.event;

import io.holunda.mesooncome.api.Task;
import lombok.Value;

@Value
public class ClaimTaskEvent implements TaskEvent{
    private final String id;
    private final String userId;
    private final Task task;
}
