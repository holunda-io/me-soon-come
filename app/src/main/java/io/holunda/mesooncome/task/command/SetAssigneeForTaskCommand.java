package io.holunda.mesooncome.task.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class SetAssigneeForTaskCommand {
    @TargetAggregateIdentifier
    private final String id;
    private final String userId;

}
