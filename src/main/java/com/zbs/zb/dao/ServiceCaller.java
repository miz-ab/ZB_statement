package com.zbs.zb.dao;


import com.zbs.zb.db_model.Post;
import com.zbs.zb.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import static com.zbs.zb.constants.Constants.*;


@Slf4j
@Service
//@EnableRetry
public class ServiceCaller {

    private final RestClient restClient;

    public ServiceCaller() {
        String basicAuth = "Basic " + Base64.getEncoder().encodeToString((HOST_USERNAME +":" +HOST_PASSWORD).getBytes());
        log.info("basic auth {}", basicAuth);
        restClient = RestClient.builder()
                .baseUrl(EXTERNAL_HOST_ADDRESS)
                .defaultHeader(HttpHeaders.AUTHORIZATION, basicAuth)
                .build();
    }

    public List<Post> findAll() {
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Post>>() {});
    }

    @Retryable(value = { IOException.class }, maxAttempts = 5, backoff = @Backoff(delay = 5000))
    public Statement getStatement() {
        System.out.println("trying "+ new Date());
        return restClient.get()
                .uri("/get-statement")
                .retrieve()
                .body(new ParameterizedTypeReference<Statement>() {});
    }

    public Post create(Post post) {
        return restClient.post()
                .uri("/get-statement")
                .contentType(MediaType.ALL)
                .body(post)
                .retrieve()
                .body(Post.class);
    }

    public String callStatement(T req){
        log.info("request var {}", req);
        return restClient.post()
                .uri("/accountBalance")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(req)
                .retrieve()
                .body(String.class);
    }

    public AccountBalanceResponse  bankStatementBalance(AccountBalance t){
        log.info("bank statement request class {}", t);
        return restClient.post()
                .uri("/accountBalance")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(t)
                .retrieve()
                .body(AccountBalanceResponse.class);
    }

    public Statement bankStatement(StatementRequest t){
        log.info("statement request {}", t);
        return restClient.post()
                .uri("/accountStatement")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(t)
                .retrieve()
                .body(Statement.class);
    }


}
