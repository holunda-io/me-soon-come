package io.holunda.mesooncome.model.es;

import lombok.Data;

@Data
public class Shards {
    private long total;
    private long successful;
    private long skipped ;
    private long failed;
}
