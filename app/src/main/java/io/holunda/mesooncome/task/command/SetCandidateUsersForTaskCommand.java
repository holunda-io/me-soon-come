package io.holunda.mesooncome.task.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Value
public class SetCandidateUsersForTaskCommand {
    @TargetAggregateIdentifier
    private final String id;
    private final List<String> userIds;

}
