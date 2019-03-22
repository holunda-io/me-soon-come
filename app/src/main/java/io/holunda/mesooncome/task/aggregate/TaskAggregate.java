package io.holunda.mesooncome.task.aggregate;

import io.holunda.mesooncome.api.Task;
import io.holunda.mesooncome.api.TaskConverter;
import io.holunda.mesooncome.task.command.CreateTaskCommand;
import io.holunda.mesooncome.task.command.RegisterTaskCreatedResultCommand;
import io.holunda.mesooncome.task.domain.TaskState;
import io.holunda.mesooncome.task.event.CreateTaskEvent;
import io.holunda.mesooncome.task.event.RegisterTaskCreatedResultEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.stereotype.Component;

import static io.holunda.mesooncome.task.domain.TaskState.CREATED;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Slf4j
@Component
public class TaskAggregate {

    @AggregateIdentifier
    private String id;
    private Object taskAsObject;
    private Task task;
    private TaskState state = null;

    @CommandHandler
    public TaskAggregate(CreateTaskCommand command) {
        apply(new CreateTaskEvent(command.getId(),
                command.getTask()));
    }

    @EventSourcingHandler
    public void on(CreateTaskEvent event, TaskConverter converter) {
        id = event.getId();
        taskAsObject = event.getTask();
        task = converter.convertObjectToTask(taskAsObject);
    }

    @CommandHandler
    public void handle(RegisterTaskCreatedResultCommand command) {
        apply(new RegisterTaskCreatedResultEvent(command.getId(),
                command.getTaskAddedResult()));
    }

    @EventSourcingHandler
    public void on(RegisterTaskCreatedResultEvent event) {
        state=CREATED;
        log.info("" + event);
    }

    protected TaskAggregate() {
    }


}
