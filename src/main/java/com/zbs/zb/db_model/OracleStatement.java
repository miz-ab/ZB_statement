package com.zbs.zb.db_model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//@NoArgsConstructor
public class OracleStatement {

    private String STATEMENT_NUMBER;
    private String BANK_ACCOUNT_NUM;
    private String STATEMENT_DATE;
    private String BANK_NAME;
    private String BANK_BRANCH_NAME;
    private Double CONTROL_BEGIN_BALANCE;
    private Double CONTROL_END_BALANCE;
    private String RECORD_STATUS_FLAG;
    private String CURRENCY_CODE;
    private String ORG_ID;
}
