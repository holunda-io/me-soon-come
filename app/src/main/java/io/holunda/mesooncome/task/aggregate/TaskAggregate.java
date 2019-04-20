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
    public TaskAggregate(CreateTaskCommand command, TaskConverter converter) {
        apply(new CreateTaskEvent(command.getId(),
                command.getTask(),
                converter.convertObjectToTask(command.getTask())));
    }

    @CommandHandler
    public void handle(ClaimTaskCommand command) {
        checkState(NEW, UNASSIGENED, ASSIGNED, DELEGATED);
        apply(new ClaimTaskEvent(command.getId(),
                command.getUserId(),
                task));
    }

    @CommandHandler
    public void handle(UnclaimTaskCommand command) {
        checkState(ASSIGNED);
        apply(new UnclaimTaskEvent(command.getId(), task));
    }

    @CommandHandler
    public void handle(CompleteTaskCommand command) {
        checkState(ASSIGNED);
        apply(new CompleteTaskEvent(command.getId(),
                task));
    }

    @CommandHandler
    public void handle(SetAssigneeForTaskCommand command) {
        checkState(NEW, UNASSIGENED, ASSIGNED);
        apply(new SetAssigneeForTaskEvent(command.getId(),
                command.getUserId(),
                task));
    }

    @CommandHandler
    public void handle(SetCandidateUsersForTaskCommand command) {
        checkState(NEW, UNASSIGENED);
        apply(new SetCandidateUsersForTaskEvent(command.getId(),
                command.getUserIds(),
                task));
    }

    @CommandHandler
    public void handle(DelegateTaskCommand command) {
        checkState(ASSIGNED);
        apply(new DelegateTaskEvent(command.getId(),
                command.getUserId()));
    }

    @CommandHandler
    public void handle(UpdateTaskCommand command) {
        checkState(NEW, ASSIGNED, DELEGATED, UNASSIGENED);
        apply(new UpdateTaskEvent(command.getId(), command.getTask()));

    }

    @EventSourcingHandler
    public void on(CreateTaskEvent event) {
        id = event.getId();
        task = event.getTask();
        taskAsObject = event.getTaskAsObject();

        if (task.getAssignee() != null && !task.getAssignee().isEmpty()) {
            apply(new SetAssigneeForTaskEvent(id, task.getAssignee(), task));
        } else if (task.getCandidateUsers() != null && !task.getCandidateUsers().isEmpty()) {
            apply(new SetCandidateUsersForTaskEvent(id, task.getCandidateUsers(), task));
        }

        state = NEW;
    }

    @EventSourcingHandler
    public void on(DelegateTaskEvent event) {
        task.setAssignee(event.getUserId());
        state = ASSIGNED;
    }

    @EventSourcingHandler
    public void on(SetCandidateUsersForTaskEvent event) {
        task.setCandidateUsers(event.getUserIds());
        state = UNASSIGENED;
    }

    @EventSourcingHandler
    public void on(SetAssigneeForTaskEvent event) {
        task.setAssignee(event.getUserId());
        state = ASSIGNED;
    }

    @EventSourcingHandler
    public void on(CompleteTaskEvent event) {
        state = COMPLETED;
    }

    @EventSourcingHandler
    public void on(UnclaimTaskEvent event) {
        task.setAssignee(null);
        state = UNASSIGENED;
    }

    @EventSourcingHandler
    public void on(ClaimTaskEvent event) {
        task.setAssignee(event.getUserId());
        state = ASSIGNED;
    }

    private void checkState(TaskState... allowedStates) {
        Arrays.stream(allowedStates)
                .filter(a -> a.equals(state))
                .findFirst().orElseThrow(IllegalStateException::new);
    }

    protected TaskAggregate() {
    }


}
