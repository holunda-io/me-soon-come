package io.holunda.mesooncome.task.aggregate;

import io.holunda.mesooncome.api.Task;
import io.holunda.mesooncome.api.TaskConverter;
import io.holunda.mesooncome.task.command.*;
import io.holunda.mesooncome.task.domain.TaskState;
import io.holunda.mesooncome.task.event.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static io.holunda.mesooncome.task.domain.TaskState.*;
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

    @CommandHandler
    public void handle(RegisterTaskCreatedResultCommand command) {
        apply(new RegisterTaskCreatedResultEvent(command.getId(),
                command.getTaskAddedResult()));
    }

    @CommandHandler
    public void handle(ClaimTaskCommand command) {
        checkState(NEW, UNASSIGENED, ASSIGNED, DELEGATED);
        state = ASSIGNED;
        apply(new ClaimTaskEvent(command.getId(),
                command.getUserId()));
    }

    @CommandHandler
    public void handle(UnclaimTaskCommand command) {
        checkState(ASSIGNED);
        state = UNASSIGENED;
        apply(new UnclaimTaskEvent(command.getId()));
    }

    @CommandHandler
    public void handle(CompleteTaskCommand command) {
        checkState(ASSIGNED);
        state = COMPLETED;
        apply(new CompleteTaskEvent(command.getId()));
    }

    @CommandHandler
    public void handle(SetAssigneeForTaskCommand command) {
        checkState(NEW, UNASSIGENED, ASSIGNED);
        state = ASSIGNED;
        apply(new SetAssigneeForTaskEvent(command.getId(),
                command.getUserId()));
    }

    @CommandHandler
    public void handle(DelegateTaskCommand command) {
        checkState(ASSIGNED);
        state = ASSIGNED;
        apply(new DelegateTaskEvent(command.getId(),
                command.getUserId()));
    }

    @CommandHandler
    public void handle(UpdateTaskCommand command) {
        checkState(NEW, ASSIGNED, DELEGATED, UNASSIGENED);
        apply(new UpdateTaskEvent(command.getId(), command.getTask()));

    }

    @EventSourcingHandler
    public void on(CreateTaskEvent event, TaskConverter converter) {
        id = event.getId();
        taskAsObject = event.getTask();
        task = converter.convertObjectToTask(taskAsObject);
    }

    @EventSourcingHandler
    public void on(UpdateTaskEvent event, TaskConverter converter) {
        taskAsObject = event.getTask();
        task = converter.convertObjectToTask(taskAsObject);
    }

    @EventSourcingHandler
    public void on(RegisterTaskCreatedResultEvent event) {
        if (task.getAssignee() == null || task.getAssignee().isEmpty()) {
            state = NEW;
        } else {
            state = ASSIGNED;
        }
    }

    @EventSourcingHandler
    public void on(ClaimTaskEvent event) {
        task.setAssignee(event.getUserId());

    }

    @EventSourcingHandler
    public void on(UnclaimTaskEvent event) {
        task.setAssignee(null);

    }

    @EventSourcingHandler
    public void on(SetAssigneeForTaskEvent event) {
        task.setAssignee(event.getUserId());

    }

    @EventSourcingHandler
    public void on(DelegateTaskEvent event) {
        task.setAssignee(event.getUserId());

    }

    @EventSourcingHandler
    public void on(CompleteTaskEvent event) {

    }

    private void checkState(TaskState... allowedStates) {
        Arrays.stream(allowedStates)
                .filter(a -> a.equals(state))
                .findFirst().orElseThrow(IllegalStateException::new);
    }

    protected TaskAggregate() {
    }


}
