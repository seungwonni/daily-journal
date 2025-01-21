package com.rate.common.login.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;

public interface KakaoLoginServiceImpl {

    void login(String code, HttpServletRequest request) throws JsonProcessingException;
    void logout(HttpServletRequest request) throws JsonProcessingException;

}