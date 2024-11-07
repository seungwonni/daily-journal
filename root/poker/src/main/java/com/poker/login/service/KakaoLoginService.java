package com.poker.login.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poker.login.dto.KakaoOauth;
import com.poker.util.RestApiRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KakaoLoginService implements KakaoLoginServiceImpl {
    @Override
    public Map login(String code) throws JsonProcessingException {
        KakaoOauth oauth = getOauth(code);
        return getUserInfo(oauth);
    }
    private KakaoOauth getOauth(String code) throws JsonProcessingException {
        String url = "https://kauth.kakao.com/oauth/token";
        // Header set
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE) + ";charset=utf-8"));

        // Body set
        MultiValueMap<String,Object> body = new LinkedMultiValueMap<>();

        body.add("grant_type", "authorization_code");
        body.add("client_id", "0019eecef6bca871e67689caaad670a5");
        body.add("redirect_uri", "http://localhost:8080/kakao-login");
        body.add("code", code);

        String response = RestApiRequest.request("post", url, body, headers);

        // Response 파싱
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        return objectMapper.readValue(response, KakaoOauth.class);
    }

    private Map getUserInfo(KakaoOauth oauth) {

        String url = "https://kapi.kakao.com/v2/user/me";
        // Header set
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE) + ";charset=utf-8"));
        headers.set("Authorization", "bearer " + oauth.getAccess_token());

        // Body set
        MultiValueMap<String,Object> body = new LinkedMultiValueMap<>();
        String response = RestApiRequest.request("post", url, body, headers);
        // Response 파싱
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

        try {
            return objectMapper.readValue(response, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

