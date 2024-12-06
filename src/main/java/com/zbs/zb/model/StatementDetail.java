package com.zbs.zb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatementDetail {

    @JsonProperty("CUSTOMER_NAME")
    private String CUSTOMER_NAME;

    @JsonProperty("ACCOUNT_NUMBER")
    private String ACCOUNT_NUMBER;

    @JsonProperty("BRANCH")
    private String BRANCH;

    @JsonProperty("TRANSACTION_REF_NO")
    private String TRANSACTION_REF_NO;

    @JsonProperty("TRANSACTION_DATE")
    private String TRANSACTION_DATE;

    @JsonProperty("OPENING_BALANCE")
    private Double OPENING_BALANCE;

    @JsonProperty("CLOSING_BALANCE")
    private Double CLOSING_BALANCE;

    @JsonProperty("VALUE_DATE")
    private String VALUE_DATE;

    @JsonProperty("DEBIT_AMOUNT")
    private Double DEBIT_AMOUNT;

    @JsonProperty("CREDIT_AMOUNT")
    private Double CREDIT_AMOUNT;

    @JsonProperty("ACCOUNT_CCY")
    private String ACCOUNT_CCY;

    @JsonProperty("INSTRUMENT_NO")
    private String INSTRUMENT_NO;

    @JsonProperty("TRANSACTION_DESC")
    private String TRANSACTION_DESC;

    @JsonProperty("NARRATIVE")
    private String NARRATIVE;
}
