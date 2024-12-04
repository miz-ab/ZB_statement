package com.zbs.zb.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestModel {
    @JsonProperty("ACCOUNT_NUMBER")
    private String ACCOUNT_NUMBER;
    @JsonProperty("START_DATE")
    private String START_DATE;
    @JsonProperty("END_DATE")
    private String END_DATE;
}
