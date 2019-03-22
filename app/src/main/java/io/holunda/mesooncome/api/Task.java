package io.holunda.mesooncome.api;

import javax.annotation.Nonnull;

public interface Task {
    @Nonnull
    String getId();

    @Nonnull
    String getName();

    @Nonnull
    String getDescription();

    String getAssignee();

    String getOwner();

    String getDelegationState();

    String getDue();

    String getFollowUp();

    Long getPriority();

    String getParentTaskId();

    String getCaseInstanceId();

    String getTenantId();
}
