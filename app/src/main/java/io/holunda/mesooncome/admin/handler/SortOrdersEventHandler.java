package io.holunda.mesooncome.admin.handler;

import io.holunda.mesooncome.admin.event.AddSortOrderPropertyEvent;
import io.holunda.mesooncome.admin.event.UpdateSortOrderPropertyEvent;
import io.holunda.mesooncome.admin.query.FindAllSortOrdersQuery;
import io.holunda.mesooncome.admin.query.SortOrder;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.holunda.mesooncome.admin.query.Order.ASC;
import static io.holunda.mesooncome.admin.query.Order.DESC;

@Service
public class SortOrdersEventHandler {

    private final Map<String, SortOrder> sortOrders = new HashMap<>();

    @EventHandler
    public void on(AddSortOrderPropertyEvent event) {
        String id = event.getId();
        sortOrders.put(id, SortOrder.builder()
                .id(id)
                .order("asc".equals(event.getOrder()) ? ASC : DESC)
                .path(event.getPath())
                .build());
    }

    @EventHandler
    public void on(UpdateSortOrderPropertyEvent event) {

        sortOrders.computeIfPresent(event.getId(), (orderId, sortOrder) ->
                SortOrder.builder()
                        .id(event.getId())
                        .order("asc".equals(event.getOrder()) ? ASC : DESC)
                        .path(event.getPath())
                        .build()
        );
    }


    @QueryHandler
    public List<SortOrder> handle(FindAllSortOrdersQuery query) {
        return new ArrayList<>(sortOrders.values());
    }

}
