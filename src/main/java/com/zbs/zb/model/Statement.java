package com.zbs.zb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.zbs.zb.model.StatementDetails;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statement {

    @JsonProperty("STATUS")
    private String STATUS;

    @JsonProperty("STATUS_CODE")
    private Integer STATUS_CODE;

    @JsonProperty("MESSAGE")
    private String MESSAGE;

    @JsonProperty("CUSTOMER_NAME")
    private String CUSTOMER_NAME;

    @JsonProperty("ACCOUNT_TYPE")
    private String ACCOUNT_TYPE;

    @JsonProperty("ACCOUNT_CCY")
    private String ACCOUNT_CCY;

    @JsonProperty("ACCOUNT_NUMBER")
    private String ACCOUNT_NUMBER;

    @JsonProperty("STATEMENT_PERIOD")
    private String STATEMENT_PERIOD;

    @JsonProperty("OPENING_BALANCE")
    private Double OPENING_BALANCE;

    @JsonProperty("CLOSING_BALANCE")
    private Double CLOSING_BALANCE;

    @JsonProperty("STATEMENT_DETAILS")
    private StatementDetails STATEMENT_DETAILS;
}
