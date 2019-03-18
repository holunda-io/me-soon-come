package io.holunda.mesooncome.admin.aggregate;

import io.holunda.mesooncome.admin.command.AddSortOrderPropertyCommand;
import io.holunda.mesooncome.admin.event.AddSortOrderPropertyEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class SortOrderAggregateTest {
    private FixtureConfiguration<AdminAggregate> fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(AdminAggregate.class);
    }

    @Test
    public void whenHandlesAddSortOrderPropertyCommandItShouldProduceAnAddSortOrderPropertyEvent() {

        String id = UUID.randomUUID().toString();
        String path = "path";
        String order = "order";
        fixture.givenNoPriorActivity()
                .when(new AddSortOrderPropertyCommand(id, path, order))
                .expectEvents(new AddSortOrderPropertyEvent(id, path, order));

    }

}