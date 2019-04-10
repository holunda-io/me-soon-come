package io.holunda.mesooncome.task.event;

import io.holunda.mesooncome.api.Task;

public interface TaskEvent {
    String getId();
    Task getTask();
}
