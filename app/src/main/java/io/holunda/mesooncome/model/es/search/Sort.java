package io.holunda.mesooncome.model.es.search;

import java.util.HashMap;

public class Sort extends HashMap<String, Order> {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String property;
        private Order order;

        public Builder order(String property, Order order) {
            this.property = property;
            this.order = order;
            return this;
        }

        public Sort build() {
            Sort sort = new Sort();
            sort.put(property, order);
            return sort;
        }
    }

}
