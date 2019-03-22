package io.holunda.mesooncome.task.controller;

import io.holunda.mesooncome.api.Task;
import io.holunda.mesooncome.api.TaskConverter;
import io.holunda.mesooncome.task.command.CreateTaskCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.UUID;

@RestController
@Slf4j
@AllArgsConstructor
public class TaskController {
    private final CommandGateway commandGateway;
    private final TaskConverter taskConverter;

    @RequestMapping(value = "/task/create", method = RequestMethod.POST)
    String create(@RequestBody Object task) {
        validateTask(task);
        String id = UUID.randomUUID().toString();
        commandGateway.send(new CreateTaskCommand(id, task));
        return id;
    }

    private void validateTask(Object taskAsObject) {
        Task task = taskConverter.convertObjectToTask(taskAsObject);
        Objects.requireNonNull(task, "task must not be null");
        Objects.requireNonNull(task.getId(), "task-id must not be null");
        Objects.requireNonNull(task.getName(), "task-name must not be null");
        Objects.requireNonNull(task.getDescription(), "task-description must not be null");


    }
}
