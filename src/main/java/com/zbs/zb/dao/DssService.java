package com.zbs.zb.dao;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zbs.zb.cli_model.SummaryReport;
import com.zbs.zb.cli_model.summaryRequest;
import com.zbs.zb.model.Login;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.Base64;
import static com.zbs.zb.constants.Constants.*;
import org.springframework.http.MediaType;

@Service
@Slf4j
public class DssService {

    private final RestClient restClient;

    public DssService(){
        String basicAuth = "Basic " + Base64.getEncoder().encodeToString((HOST_USERNAME +":" +HOST_PASSWORD).getBytes());
        log.info("basic auth {}", basicAuth);
        restClient = RestClient.builder()
                .baseUrl(DSS_HOST)
                .build();
    }

    public String getToken(){
        String tknResponse = null;
        String response = restClient.post()
                .uri("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(new Login(CLI_EMAIL, CLI_PASSWORD))
                .retrieve()
                .body(String.class);
        try{
            if(response != null){
                JsonObject root = JsonParser.parseString(response).getAsJsonObject();
                tknResponse = root.getAsJsonObject("data").get("accessToken").getAsString();
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return tknResponse;
    }

    /*
    public SummaryReport getSummaryReport(summaryRequest summaryRequest){
        String tkn = getToken();
        log.info("token {}", tkn);
        if(tkn != null){
            return restClient.get()
                    .uri("/reports/do-daily-financial-summary-report")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + tkn)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(summaryRequest)
                    .retrieve()
                    .body(SummaryReport.class);
        }
        return null;
    }
    */
}
