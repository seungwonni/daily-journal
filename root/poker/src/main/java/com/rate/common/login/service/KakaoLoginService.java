package com.rate.common.login.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rate.common.login.dto.KakaoOauth;
import com.rate.common.login.util.RestApiRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public void login(String code, HttpServletRequest request) throws JsonProcessingException {
        KakaoOauth oauth = getOauth(code);
        Map map = getUserInfo(oauth);
        setLoginSession(map, request, oauth);
    }

    @Override
    public void logout(HttpServletRequest request) throws JsonProcessingException {
        String url = "https://kapi.kakao.com/v1/user/unlink";
        HttpHeaders headers = new HttpHeaders();
        HttpSession session = request.getSession();
        String accessToken = (String) session.getAttribute("KakaoAccessToken");
        headers.set("Authorization", "Bearer " + accessToken);

        // Body set
        MultiValueMap<String,Object> body = new LinkedMultiValueMap<>();

        body.add("target_id_type", "authorization_code");
        body.add("target_id", Long.valueOf((String) session.getAttribute("userId")));

        String response = RestApiRequest.request("post", url, body, headers);

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

    private void setLoginSession(Map map, HttpServletRequest request, KakaoOauth oauth) {
        String userId= String.valueOf(map.get("id"));
        String nickname = (String) ((Map) map.get("properties")).get("nickname");
        request.getSession().setAttribute("userId", userId);
        request.getSession().setAttribute("nickname", nickname);
        request.getSession().setAttribute("KakaoAccessToken", oauth.getAccess_token());
    }
}

