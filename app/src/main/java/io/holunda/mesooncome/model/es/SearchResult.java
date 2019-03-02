package io.holunda.mesooncome.model.es;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SearchResult {

    private long took;
    @JsonProperty("timed_out")
    private boolean timedOut;

    @JsonProperty("_shards")
    private Shards shards;
    private Hits hits;
}
