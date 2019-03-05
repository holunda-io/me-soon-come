package io.holunda.mesooncome.database.init;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@ChangeLog
@Slf4j
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "000.001", author = "bignesta")
    public void importantInitialRequestBody(DB db) {

        DBCollection collection = db.getCollectionFromString("requestBody");

        String file = "/db/request_body.json";
        String json = new Scanner(DatabaseChangelog
                .class
                .getResourceAsStream(file), "UTF-8")
                .useDelimiter("\\A")
                .next();

        collection.insert(BasicDBObject.parse(json));
        log.info("Imported from file {}:{}", file, json);
    }


}
