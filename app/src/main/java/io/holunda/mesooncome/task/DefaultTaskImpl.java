package io.holunda.mesooncome.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.holunda.mesooncome.api.Task;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class DefaultTaskImpl implements Task {
    private String id;
    private String name;
    private String description;
    private String assignee;
    private String owner;
    private String delegationState;
    private String due;
    private String followUp;
    private Long priority;
    private String parentTaskId;
    private String caseInstanceId;
    private String tenantId;
}
