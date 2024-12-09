package com.zbs.zb.dao;

import com.zbs.zb.model.Login;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Base64;

import static com.zbs.zb.constants.Constants.*;
import org.springframework.http.HttpHeaders;
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

    public String getToken(Login l){
        String response = restClient.post()
                .uri("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(l)
                .retrieve()
                .body(String.class);
        return response;
    }
}
