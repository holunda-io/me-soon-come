package io.holunda.mesooncome.task.handler;

import lombok.Data;

@Data
public class TaskAddedResult {

    private String _index;
    private String _type;
    private String _id;
    private String _version;
    private String result;
    private Shards _shards;
    private String _seq_no;
    private String _primary_term;

    @Data
    public class Shards {
        private long total;
        private long successful;
        private long failed;

    }
}
