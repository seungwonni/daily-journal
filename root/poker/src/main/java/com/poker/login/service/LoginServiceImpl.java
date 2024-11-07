package com.poker.login.service;

import com.poker.login.entity.LoginEntity;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public interface LoginServiceImpl {

    Integer save(LoginEntity login);

    Map<String, Object> login(LoginEntity login, HttpServletRequest request);

}