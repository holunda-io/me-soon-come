package io.holunda.mesooncome.controller;

import io.holunda.mesooncome.database.RequestBodyRepository;
import io.holunda.mesooncome.model.db.RequestBodyEntity;
import io.holunda.mesooncome.model.es.Index;
import io.holunda.mesooncome.service.ElasticsearchService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class AdminController {

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
