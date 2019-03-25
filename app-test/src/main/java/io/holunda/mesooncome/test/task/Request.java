package io.holunda.mesooncome.test.task;


import io.holunda.mesooncome.api.Task;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Request {

    private final Task task;
}