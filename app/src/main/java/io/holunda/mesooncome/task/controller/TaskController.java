package io.holunda.mesooncome.task.controller;

import io.holunda.mesooncome.api.Task;
import io.holunda.mesooncome.api.TaskConverter;
import io.holunda.mesooncome.task.command.*;
import io.holunda.mesooncome.task.domain.User;
import io.holunda.mesooncome.task.query.FindTaskById;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@Slf4j
@AllArgsConstructor
public class TaskController {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final TaskConverter taskConverter;

    @RequestMapping(value = "/task/create", method = RequestMethod.POST)
    @CrossOrigin("http://localhost:3000")
    public String create(@RequestBody Object task) {
        validateTask(task);
        String id = UUID.randomUUID().toString();
        commandGateway.send(new CreateTaskCommand(id, task));
        return id;
    }

    @RequestMapping(value = "/task/{id}/", method = RequestMethod.PUT)
    @CrossOrigin("http://localhost:3000")
    public void update(@PathVariable(name = "id") String id, @RequestBody Object task) {
        validateTask(task);
        commandGateway.send(new UpdateTaskCommand(id, task));
    }

    @RequestMapping(value = "/task/{id}/claim", method = RequestMethod.POST)
    @CrossOrigin("http://localhost:3000")
    public void claim(@PathVariable(name = "id") String id, @RequestBody User user) {
        commandGateway.send(new ClaimTaskCommand(id, user.getUserId()));
    }

    @RequestMapping(value = "/task/{id}/unclaim", method = RequestMethod.POST)
    @CrossOrigin("http://localhost:3000")
    public void unclaim(@PathVariable(name = "id") String id) {
        commandGateway.send(new UnclaimTaskCommand(id));
    }

    @RequestMapping(value = "/task/{id}/complete", method = RequestMethod.POST)
    @CrossOrigin("http://localhost:3000")
    public void complete(@PathVariable(name = "id") String id) {
        commandGateway.send(new CompleteTaskCommand(id));
    }

    @RequestMapping(value = "/task/{id}/assignee", method = RequestMethod.POST)
    @CrossOrigin("http://localhost:3000")
    public void setAssignee(@PathVariable(name = "id") String id, @RequestBody User user) {
        commandGateway.send(new SetAssigneeForTaskCommand(id, user.getUserId()));
    }

    @RequestMapping(value = "/task/{id}/candidateUsers", method = RequestMethod.POST)
    @CrossOrigin("http://localhost:3000")
    public void setCandidateUsers(@PathVariable(name = "id") String id, @RequestBody List<User> users) {
        commandGateway.send(new SetCandidateUsersForTaskCommand(id, users
                .stream()
                .map(u -> u.getUserId())
                .collect(Collectors.toList())));
    }

    @RequestMapping(value = "/task/{id}/delegate", method = RequestMethod.POST)
    @CrossOrigin("http://localhost:3000")
    public void delegate(@PathVariable(name = "id") String id, @RequestBody User user) {
        commandGateway.send(new SetAssigneeForTaskCommand(id, user.getUserId()));
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.GET)
    @CrossOrigin("http://localhost:3000")
    public Task get(@PathVariable(name = "id") String id) {
        return queryGateway
                .query(FindTaskById.builder().id(id).build(), ResponseTypes.instanceOf(Task.class))
                .join();
    }


    private void validateTask(Object taskAsObject) {
        Task task = taskConverter.convertObjectToTask(taskAsObject);
        Objects.requireNonNull(task, "task must not be null");

        Optional.ofNullable(task.getName()).filter(n -> !n.isEmpty()).orElseThrow(IllegalArgumentException::new);
        Optional.ofNullable(task.getDescription()).filter(n -> !n.isEmpty()).orElseThrow(IllegalArgumentException::new);
    }
}
