package io.holunda.mesooncome.task.command;

import io.holunda.mesooncome.task.handler.TaskAddedResult;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class RegisterTaskCreatedResultCommand {
    @TargetAggregateIdentifier
    private final String id;
    private final TaskAddedResult taskAddedResult;

}
