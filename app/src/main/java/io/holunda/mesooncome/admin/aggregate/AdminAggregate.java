package io.holunda.mesooncome.admin.aggregate;

import io.holunda.mesooncome.admin.command.AddSortOrderPropertyCommand;
import io.holunda.mesooncome.admin.command.UpdateSortOrderPropertyCommand;
import io.holunda.mesooncome.admin.event.AddSortOrderPropertyEvent;
import io.holunda.mesooncome.admin.event.UpdateSortOrderPropertyEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class AdminAggregate {

    @AggregateIdentifier
    private String id;
    private String path;
    private String order;

    @CommandHandler
    public AdminAggregate(AddSortOrderPropertyCommand command) {
        apply(new AddSortOrderPropertyEvent(command.getId(),
                command.getPath(),
                command.getOrder()));
    }

    @CommandHandler
    public void handle(UpdateSortOrderPropertyCommand command){
        apply(new UpdateSortOrderPropertyEvent(command.getId(),
                command.getPath(),
                command.getOrder()));
    }

    @EventSourcingHandler
    public void on(AddSortOrderPropertyEvent event) {
        this.id = event.getId();
        this.path = event.getPath();
        this.order = event.getOrder();
    }
    @EventSourcingHandler
    public void on(UpdateSortOrderPropertyEvent event) {
        this.path = event.getPath();
        this.order = event.getOrder();
    }

    protected AdminAggregate() {
    }
}