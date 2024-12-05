package com.zbs.zb.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountBalanceResponse {

    @JsonProperty("STATUS")
    private String STATUS;

    @JsonProperty("STATUS_CODE")
    private int STATUS_CODE;

    @JsonProperty("ACCOUNT_NUMBER")
    private String ACCOUNT_NUMBER;

    @JsonProperty("ACCOUNT_CCY")
    private String ACCOUNT_CCY;

    @JsonProperty("AVAILABLE_BALANCE")
    private Double AVAILABLE_BALANCE;

    @JsonProperty("MESSAGE")
    private String MESSAGE;

    @JsonProperty("TIMESTAMP")
    private String TIMESTAMP;
}
