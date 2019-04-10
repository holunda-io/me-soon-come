package io.holunda.mesooncome.task.controller;


import io.holunda.mesooncome.api.Task;
import io.holunda.mesooncome.task.event.CreateTaskEvent;
import io.holunda.mesooncome.task.event.SetCandidateUsersForTaskEvent;
import io.holunda.mesooncome.task.event.TaskEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxProcessor;
import reactor.core.publisher.FluxSink;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/taskboard")
@Slf4j
public class TaskboardController {


    private final FluxProcessor processor = DirectProcessor.create().serialize();
    private final FluxSink sink = processor.sink();
    private Map<String, ServerSentTaskEvent> newTasks = new HashMap<>();
    private Map<String, ServerSentTaskEvent> unassignedTasks = new HashMap<>();


    @GetMapping(path = "/taskboardEvents", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @CrossOrigin("http://localhost:3000")
    public Flux getTaskboardEvents() {
        return processor.map(s -> s);
    }

    @GetMapping(path = "/newTasks", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin("http://localhost:3000")
    public Collection<ServerSentTaskEvent> getNewTasks() {
        return newTasks.values();
    }

    @GetMapping(path = "/unassignedTasks", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin("http://localhost:3000")
    public Collection<ServerSentTaskEvent> getUnassignedTasks() {
        return unassignedTasks.values();
    }

    @EventHandler
    public void handle(CreateTaskEvent event) {
        ServerSentEvent serverSentEvent = createServerSentEvent(event);
        newTasks.put(event.getId(), ServerSentTaskEvent.from(serverSentEvent));
        sink.next(serverSentEvent);
    }

    private ServerSentEvent<Task> createServerSentEvent(TaskEvent event) {
        return ServerSentEvent.<Task>builder()
                .id(event.getId())
                .data(event.getTask())
                .event(event.getClass().getSimpleName())
                .build();
    }

    @EventHandler
    public void handle(SetCandidateUsersForTaskEvent event) {
        ServerSentEvent serverSentEvent = createServerSentEvent(event);
        newTasks.remove(event.getId());
        unassignedTasks.put(event.getId(), ServerSentTaskEvent.from(serverSentEvent));

        sink.next(serverSentEvent);
    }

}
