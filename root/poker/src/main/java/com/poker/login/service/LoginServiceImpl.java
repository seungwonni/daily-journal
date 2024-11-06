package com.poker.login.service;

import com.poker.login.entity.LoginEntity;

public interface LoginServiceImpl {

    Integer save(LoginEntity login);

    Integer login(LoginEntity login);

}