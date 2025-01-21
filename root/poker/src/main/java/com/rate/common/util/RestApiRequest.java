package com.rate.common.login.util;

import org.springframework.http.HttpEntity;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RestApiRequest {
    public static String request(String method, String url, MultiValueMap<String, Object> body, HttpHeaders headers) {
        RestTemplate restTemplate = new RestTemplate();
        // Message
        HttpEntity<?> requestMessage = new HttpEntity<>(body, headers);
        // Request
        HttpEntity<String> response = restTemplate.postForEntity(url, requestMessage, String.class);
        // Response 파싱
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        return response.getBody();
    }
}
