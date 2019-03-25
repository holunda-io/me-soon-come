package io.holunda.mesooncome.task.handler;

import io.holunda.mesooncome.api.Task;
import io.holunda.mesooncome.task.event.CreateTaskEvent;
import io.holunda.mesooncome.task.query.FindTaskById;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class TaskListEventHandler {

    private Map<String, Task> taskList = new HashMap<>();

    @EventHandler
    public void handle(CreateTaskEvent event) {
        taskList.put(event.getId(), event.getTask());
    }

    @QueryHandler
    public Task handle(FindTaskById query) {
        return taskList.get(query.getId());
    }

}
