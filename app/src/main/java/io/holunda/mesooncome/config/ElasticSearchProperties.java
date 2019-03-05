package io.holunda.mesooncome.config;

import lombok.Builder;

@Builder
public class ElasticSearchProperties {

    private String host;
    private String port;

    public String getUrl() {
        return "http://" + host + ":" + port + "/";
    }

}
