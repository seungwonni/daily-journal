package com.poker.login.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public interface KakaoLoginServiceImpl {

    Map login(String code) throws JsonProcessingException;

}