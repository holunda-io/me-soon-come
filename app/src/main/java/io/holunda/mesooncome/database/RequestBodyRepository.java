package io.holunda.mesooncome.database;

import io.holunda.mesooncome.model.db.RequestBodyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface RequestBodyRepository extends MongoRepository<RequestBodyEntity, String> {
}