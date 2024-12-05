package com.zbs.zb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class StatementDetails {

    @JsonProperty("STATEMENT_DETAIL")
    private List<StatementDetail_> STATEMENT_DETAIL;
}
