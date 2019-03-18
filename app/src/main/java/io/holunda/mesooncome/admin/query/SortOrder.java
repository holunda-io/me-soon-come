package io.holunda.mesooncome.admin.query;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
public class SortOrder {

    private final String id;
    private final String path;
    private final Order order;


}
