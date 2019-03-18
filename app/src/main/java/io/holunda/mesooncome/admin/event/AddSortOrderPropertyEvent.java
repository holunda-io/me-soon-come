package io.holunda.mesooncome.admin.event;

import lombok.Value;

@Value
public class AddSortOrderPropertyEvent {
    private final String id;
    private final String path;
    private final String order;
}
