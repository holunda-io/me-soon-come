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
public class Property {
    private Map<String,Property> properties;
    private String type;
    private Map<String, Field> fields;


}
