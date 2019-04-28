package io.holunda.mesooncome.api;

import java.util.List;

public interface Task {

    String getId();

    String getName();

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
