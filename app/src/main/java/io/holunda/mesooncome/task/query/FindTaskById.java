package io.holunda.mesooncome.task.query;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class FindTaskById {
    private final String id;
}
