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

    /*

    "CUSTOMER_NAME": null,
                "TRANSACTION_REF_NO": "103ICCOETB000001",
                "OPENING_BALANCE": 0,
                "CLOSING_BALANCE": 41061.23,
                "VALUE_DATE": "2023-07-01T00:00:00.000+03:00",
                "DEBIT_AMOUNT": 0,
                "CREDIT_AMOUNT": 229.17,
                "ACCOUNT_CCY": "ETB",
                "TRANSACTION_DESC": "Interest on CASA - Liquidation",
                "NARRATIVE": "--103ICCOETB000001 "
    * */
}
