package io.holunda.mesooncome.model.es;

import lombok.Data;

@Data
public class Payload {
    private double amount;
    private String currency;
    private String id;
    private String subject;
    private String applicant;
}
