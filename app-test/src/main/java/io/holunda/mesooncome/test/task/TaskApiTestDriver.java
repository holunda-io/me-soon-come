package io.holunda.mesooncome.test.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.holunda.mesooncome.api.Task;
import io.holunda.mesooncome.task.DefaultTaskImpl;
import io.holunda.mesooncome.task.domain.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

@Data
@Slf4j
public class TaskApiTestDriver implements Task {
    private String id;
    private String name;
    private String description;
    private String assignee;
    private String owner;
    private String delegationState;
    private String due;
    private String followUp;
    private Long priority;
    private String parentTaskId;
    private String caseInstanceId;
    private String tenantId;

    @JsonIgnore
    private String newId;

    @JsonIgnore
    private final Client client = ClientBuilder.newClient();

    public String create() {

        Request request = Request.builder()
                .task(this)
                .build();

        try {

            newId = client
                    .target("http://localhost:8080/task/create")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(request, MediaType.APPLICATION_JSON), String.class);
        } catch (InternalServerErrorException e) {
            return e.getResponse().getStatusInfo().getReasonPhrase();
        }
        return "OK";
    }

    public String claim() {

        User request = User.builder()
                .userId("me")
                .build();

        try {

            client
                    .target("http://localhost:8080/task/" + newId + "/claim")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(request, MediaType.APPLICATION_JSON));

        } catch (InternalServerErrorException e) {
            return e.getResponse().getStatusInfo().getReasonPhrase();
        }
        return "OK";
    }

    public synchronized String get() throws InterruptedException {

        DefaultTaskImpl task = null;
        boolean success = false;
        int times = 0;
        while (success == false || times > 100) {
            times++;
            try {

                task = client
                        .target("http://localhost:8080/task/" + newId)
                        .request(MediaType.APPLICATION_JSON)
                        .get(DefaultTaskImpl.class);

                success = task != null;

            } catch (InternalServerErrorException e) {
                return e.getResponse().getStatusInfo().getReasonPhrase();
            }
            wait(5);

        }

        return id.equals(task.getId()) ? "OK" : "FAILURE";
    }

}
