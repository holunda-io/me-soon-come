package io.holunda.mesooncome.model.es;

import io.holunda.mesooncome.model.db.SortEntity;

import java.util.HashMap;

public class Sort extends HashMap<String, Order> {

    public static Builder builder() {
        return new Builder();
    }

    public static Sort from(SortEntity entity) {

        return Sort.builder()
                .order(entity.getPath(), Order.builder()
                        .order(entity.getOrder())
                        .build())
                .build();
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
