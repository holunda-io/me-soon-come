package io.holunda.mesooncome.model.es;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Index {
    private Object aliases;
    private Map<String,Mapping> mappings;
    private Object settings;

}
