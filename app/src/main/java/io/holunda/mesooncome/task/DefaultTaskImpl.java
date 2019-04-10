package io.holunda.mesooncome.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.holunda.mesooncome.api.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
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
    private List<String> candidateUsers;
}
