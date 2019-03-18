package io.holunda.mesooncome.admin.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class AddSortOrderPropertyCommand {

    @TargetAggregateIdentifier
    private final String id;
    private final String path;
    private final String order;

}
