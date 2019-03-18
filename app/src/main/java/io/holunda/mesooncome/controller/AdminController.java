package io.holunda.mesooncome.controller;

import io.holunda.mesooncome.admin.command.AddSortOrderPropertyCommand;
import io.holunda.mesooncome.admin.command.UpdateSortOrderPropertyCommand;
import io.holunda.mesooncome.admin.query.FindAllSortOrdersQuery;
import io.holunda.mesooncome.admin.query.SortOrder;
import io.holunda.mesooncome.database.RequestBodyRepository;
import io.holunda.mesooncome.model.db.RequestBodyEntity;
import io.holunda.mesooncome.service.ElasticsearchService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@AllArgsConstructor
public class AdminController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @RequestMapping(value = "/admin/order", method = RequestMethod.PUT)
    void addSortOrderProperty(@RequestParam(value = "path", required = true) String path,
                              @RequestParam(value = "order", required = true) String order) {
        commandGateway.send(new AddSortOrderPropertyCommand(UUID.randomUUID().toString(),
                path, order));

    }

    @RequestMapping(value = "/admin/order/{id}", method = RequestMethod.POST)
    void updateSortOrderProperty(@PathVariable(value = "id") String id,
                                 @RequestParam(value = "path", required = true) String path,
                                 @RequestParam(value = "order", required = true) String order) {
        commandGateway.send(new UpdateSortOrderPropertyCommand(id,
                path, order));

    }

    @GetMapping("/admin/orders")
    public List<SortOrder> findAllSortOrders() {
        return queryGateway
                .query(new FindAllSortOrdersQuery(), ResponseTypes.multipleInstancesOf(SortOrder.class))
                .join();
    }

    private final RequestBodyRepository requestBodyRepository;
    private final ElasticsearchService elasticsearchService;

    @RequestMapping(value = "/admin/api/configuration", method = RequestMethod.GET)
    @CrossOrigin("http://localhost:4000")
    public RequestBodyEntity getRequestBody() {

        RequestBodyEntity requestBodyEntity = requestBodyRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(IllegalStateException::new);


        return requestBodyEntity;
    }

    @RequestMapping(value = "/admin/api/properties", method = RequestMethod.GET)
    @CrossOrigin("http://localhost:4000")
    public List<String> getProperties() {

        return elasticsearchService.getIndex("task").getMappings().get("_doc").getPropertiesAsPath();


    }

}
