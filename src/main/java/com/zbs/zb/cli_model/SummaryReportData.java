package com.zbs.zb.cli_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryReportData {
    private int id;
    private String uuid;
    private String cashierCode;
    private String amountOfMoney;
    private int transactionCount;
    private String paymentType;
    private String transactionDate;
    private String batchNumber;
    private String depositBankName;
    private String bankReferenceNumber;
    private String remark;
    private String createdAt;
    private String updatedAt;


}
