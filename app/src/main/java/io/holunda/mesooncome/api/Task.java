package io.holunda.mesooncome.api;

import javax.annotation.Nonnull;
import java.util.List;

public interface Task {
    @Nonnull
    String getId();

    @Nonnull
    String getName();

    @Nonnull
    String getDescription();

    String getAssignee();

    void setAssignee(String userId);

    List<String> getCandidateUsers();

    void setCandidateUsers(List<String> userIds);

    String getOwner();

    String getDelegationState();

    String getDue();

    String getFollowUp();

    Long getPriority();

    String getParentTaskId();

    String getCaseInstanceId();

    String getTenantId();
}
