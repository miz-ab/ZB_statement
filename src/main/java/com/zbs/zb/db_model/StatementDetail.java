package com.zbs.zb.db_model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
//@NoArgsConstructor
public class StatementDetail {

    private String UID;
    private String SDID;
    private String CUSTOMER_NAME;
    private String ACCOUNT_NUMBER;
    private String BRANCH;
    private String TRANSACTION_REF_NO;
    private String TRANSACTION_DATE;
    private Double OPENING_BALANCE;
    private Double CLOSING_BALANCE;
    private String VALUE_DATE;
    private Double DEBIT_AMOUNT;
    private Double CREDIT_AMOUNT;
    private String ACCOUNT_CCY;
    private String INSTRUMENT_NO;
    private String TRANSACTION_DESC;
    private String NARRATIVE;
}

