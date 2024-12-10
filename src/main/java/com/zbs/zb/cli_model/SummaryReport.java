package com.zbs.zb.cli_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryReport {
    private boolean status;
    private int code;
    private String message;
    private ReportData data;

}