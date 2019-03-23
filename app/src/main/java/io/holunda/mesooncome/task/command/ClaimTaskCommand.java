package io.holunda.mesooncome.task.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class ClaimTaskCommand {
    @TargetAggregateIdentifier
    private final String id;
    private final String userId;

}
