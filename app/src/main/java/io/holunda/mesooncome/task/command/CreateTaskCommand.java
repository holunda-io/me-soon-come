package io.holunda.mesooncome.task.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CreateTaskCommand {
    @TargetAggregateIdentifier
    private final String id;
    private final Object task;

}
