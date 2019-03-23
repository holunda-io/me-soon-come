package io.holunda.mesooncome.task.controller;

import io.holunda.mesooncome.api.Task;
import io.holunda.mesooncome.api.TaskConverter;
import io.holunda.mesooncome.task.command.*;
import io.holunda.mesooncome.task.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@Slf4j
@AllArgsConstructor
public class TaskController {
    private final CommandGateway commandGateway;
    private final TaskConverter taskConverter;

    @RequestMapping(value = "/task/create", method = RequestMethod.POST)
    public String create(@RequestBody Object task) {
        validateTask(task);
        String id = UUID.randomUUID().toString();
        commandGateway.send(new CreateTaskCommand(id, task));
        return id;
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable(name = "id") String id,@RequestBody Object task) {
        validateTask(task);
        commandGateway.send(new UpdateTaskCommand(id, task));
    }

    @RequestMapping(value = "/task/{id}/claim", method = RequestMethod.POST)
    public void claim(@PathVariable(name = "id") String id, @RequestBody User user) {
        commandGateway.send(new ClaimTaskCommand(id, user.getUserId()));
    }

    @RequestMapping(value = "/task/{id}/unclaim", method = RequestMethod.POST)
    public void unclaim(@PathVariable(name = "id") String id) {
        commandGateway.send(new UnclaimTaskCommand(id));
    }
    @RequestMapping(value = "/task/{id}/complete", method = RequestMethod.POST)
    public void complete(@PathVariable(name = "id") String id) {
        commandGateway.send(new CompleteTaskCommand(id));
    }

    @RequestMapping(value = "/task/{id}/assignee", method = RequestMethod.POST)
    public void setAssignee(@PathVariable(name = "id") String id, @RequestBody User user) {
        commandGateway.send(new SetAssigneeForTaskCommand(id, user.getUserId()));
    }

    @RequestMapping(value = "/task/{id}/delegate", method = RequestMethod.POST)
    public void delegate(@PathVariable(name = "id") String id, @RequestBody User user) {
        commandGateway.send(new SetAssigneeForTaskCommand(id, user.getUserId()));
    }


    private void validateTask(Object taskAsObject) {
        Task task = taskConverter.convertObjectToTask(taskAsObject);
        Objects.requireNonNull(task, "task must not be null");
        Objects.requireNonNull(task.getId(), "task-id must not be null");
        Objects.requireNonNull(task.getName(), "task-name must not be null");
        Objects.requireNonNull(task.getDescription(), "task-description must not be null");


    }
}
