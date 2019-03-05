package io.holunda.mesooncome.service;

import io.holunda.mesooncome.database.RequestBodyRepository;
import io.holunda.mesooncome.model.db.RequestBodyEntity;
import io.holunda.mesooncome.model.es.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    @Autowired
    private RequestBodyRepository requestBodyRepository;

    public RequestBody getRequestBody() {

        RequestBodyEntity requestBodyEntity = requestBodyRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(IllegalStateException::new);

        return RequestBody.from(requestBodyEntity);
    }
}
