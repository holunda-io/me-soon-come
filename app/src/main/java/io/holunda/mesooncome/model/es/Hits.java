package io.holunda.mesooncome.model.es;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Hits {
    private long total;
    @JsonProperty("max_score")
    private long maxScore;
    private List<Hit> hits;

}
