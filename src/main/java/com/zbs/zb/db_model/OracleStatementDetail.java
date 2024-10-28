package com.zbs.zb.db_model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OracleStatementDetail {

    private String BANK_ACCOUNT_NUM;
    private String STATEMENT_NUMBER;
    private String LINE_NUMBER;
    private String TRX_DATE;

    private String TRX_CODE;
    private String EFFECTIVE_DATE;
    private String TRX_TEXT;
    private Double AMOUNT;

    private String CURRENCY_CODE;
    private String BANK_TRX_NUMBER;
    private Integer CREATED_BY ;
    private String CREATION_DATE;
}
