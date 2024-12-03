package com.zbs.zb.dao;


import com.zbs.zb.db_model.Post;
import com.zbs.zb.model.Statement;
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

import static com.zbs.zb.constants.Constants.EXTERNAL_HOST_ADDRESS;

@Service
//@EnableRetry
public class ServiceCaller {

    private final RestClient restClient;

    public ServiceCaller() {
        String basicAuth = "Basic " + Base64.getEncoder().encodeToString(("username:password").getBytes());
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



}
