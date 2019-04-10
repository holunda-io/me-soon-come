package io.holunda.mesooncome.task.controller;

import io.holunda.mesooncome.api.Task;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.codec.ServerSentEvent;

@Data
@Builder
public final class ServerSentTaskEvent {

    private final String id;
    private final String event;
    private final Task data;

    public static ServerSentTaskEvent from(ServerSentEvent serverSentEvent) {
        return ServerSentTaskEvent.builder()
                .id(serverSentEvent.id())
                .data((Task) serverSentEvent.data())
                .event(serverSentEvent.event())
                .build();
    }
}
