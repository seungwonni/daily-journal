package com.poker.service;

import com.poker.entity.LoginEntity;
import com.poker.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginServiceImpl {

    private final LoginRepository loginRepository;

    @Override
    public Integer save(LoginEntity login) {
        loginRepository.save(login);
        return 1;
    }
}

