package io.holunda.mesooncome.task.event;

import io.holunda.mesooncome.api.Task;
import lombok.Value;

import java.util.List;

@Value
public class SetCandidateUsersForTaskEvent implements TaskEvent{
    private final String id;
    private final List<String> userIds;
    private final Task task;
}
