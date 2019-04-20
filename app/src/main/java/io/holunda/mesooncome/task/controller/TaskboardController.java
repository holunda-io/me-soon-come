package io.holunda.mesooncome.task.controller;


import io.holunda.mesooncome.api.Task;
import io.holunda.mesooncome.task.event.*;
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
    private Map<String, ServerSentTaskEvent> todoList = new HashMap<>();
    private Map<String, ServerSentTaskEvent> inProgressList = new HashMap<>();
    private Map<String, ServerSentTaskEvent> doneList = new HashMap<>();


    @GetMapping(path = "/taskboardEvents", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @CrossOrigin("http://localhost:3000")
    public Flux getTaskboardEvents() {
        return processor.map(s -> s);
    }

    @GetMapping(path = "/todoList", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin("http://localhost:3000")
    public Collection<ServerSentTaskEvent> getTodoList() {
        return todoList.values();
    }


    @GetMapping(path = "/inProgressList", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin("http://localhost:3000")
    public Collection<ServerSentTaskEvent> getInProgressList() {
        return inProgressList.values();
    }

    @GetMapping(path = "/doneList", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin("http://localhost:3000")
    public Collection<ServerSentTaskEvent> getDoneList() {
        return doneList.values();
    }

    @EventHandler
    public void handle(CreateTaskEvent event) {
        ServerSentEvent serverSentEvent = createServerSentEvent(event);
        todoList.put(event.getId(), ServerSentTaskEvent.from(serverSentEvent));
        sink.next(serverSentEvent);
    }

    @EventHandler
    public void handle(SetAssigneeForTaskEvent event){
        ServerSentEvent serverSentEvent = createServerSentEvent(event);
        todoList.remove(event.getId());
        inProgressList.put(event.getId(), ServerSentTaskEvent.from(serverSentEvent));

        sink.next(serverSentEvent);
    }

    @EventHandler
    public void handle(SetCandidateUsersForTaskEvent event) {
        ServerSentEvent serverSentEvent = createServerSentEvent(event);
        sink.next(serverSentEvent);
    }

    @EventHandler
    public void handle(ClaimTaskEvent event) {
        ServerSentEvent serverSentEvent = createServerSentEvent(event);
        todoList.remove(event.getId());
        inProgressList.put(event.getId(), ServerSentTaskEvent.from(serverSentEvent));

        sink.next(serverSentEvent);
    }

    @EventHandler
    public void handle(UnclaimTaskEvent event) {
        ServerSentEvent serverSentEvent = createServerSentEvent(event);
        inProgressList.remove(event.getId());
        todoList.put(event.getId(), ServerSentTaskEvent.from(serverSentEvent));

        sink.next(serverSentEvent);
    }

    @EventHandler
    public void handle(CompleteTaskEvent event) {
        ServerSentEvent serverSentEvent = createServerSentEvent(event);
        inProgressList.remove(event.getId());
        doneList.put(event.getId(), ServerSentTaskEvent.from(serverSentEvent));

        sink.next(serverSentEvent);
    }

    private ServerSentEvent<Task> createServerSentEvent(TaskEvent event) {
        return ServerSentEvent.<Task>builder()
                .id(event.getId())
                .data(event.getTask())
                .event(event.getClass().getSimpleName())
                .build();
    }

}
