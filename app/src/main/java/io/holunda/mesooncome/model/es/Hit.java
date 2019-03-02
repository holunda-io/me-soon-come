package io.holunda.mesooncome.model.es;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Hit {
    @JsonProperty("_index")
    private String index;
    @JsonProperty("_type")
    private String type;
    @JsonProperty("_id")
    private String id;
    @JsonProperty("_score")
    private double score;
    @JsonProperty("_source")
    private Object source;
}
