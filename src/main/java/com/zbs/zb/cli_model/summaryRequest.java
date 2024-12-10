package com.zbs.zb.cli_model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class summaryRequest {
    private String startDate;
    private String endDate;
    private String cashierCode;
}
