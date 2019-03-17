package io.holunda.mesooncome.model.es;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mapping {
    private Map<String, Property> properties;

    public List<String> getPropertiesAsPath() {
        List<String> ret = new ArrayList<>();

        properties.forEach((k, v) -> ret.addAll(getPath(k, v, null)));

        return ret;

    }

    private List<String> getPath(String key, Property val, String parent) {
        List<String> ret = new ArrayList<>();
        final String newPath = parent == null ? key : parent + "." + key;

        if (val.getProperties() == null) {
            ret.add(newPath);
        } else {
            val.getProperties().forEach((k, v) -> ret.addAll(getPath(k, v, newPath)));
        }
        return ret;
    }

}
