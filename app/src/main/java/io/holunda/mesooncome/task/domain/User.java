package io.holunda.mesooncome.task.domain;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class User {
    @NotNull
    private final String userId;
}
