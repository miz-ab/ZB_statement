package com.zbs.zb.cli_model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportData {

    private List<SummaryReportData> summaryReportDataList;
}
