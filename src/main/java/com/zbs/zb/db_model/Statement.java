package com.zbs.zb.db_model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.zbs.zb.model.StatementDetails;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statement {

    private String UID;
    private String STATUS;
    private Integer STATUS_CODE;
    private String MESSAGE;
    private String CUSTOMER_NAME;
    private String ACCOUNT_TYPE;
    private String ACCOUNT_CCY;
    private String ACCOUNT_NUMBER;
    private String STATEMENT_PERIOD;
    private Double OPENING_BALANCE;
    private Double CLOSING_BALANCE;
    //private StatementDetails STATEMENT_DETAILS;
}

