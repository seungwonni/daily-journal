package com.poker.login.service;

import com.poker.login.entity.LoginEntity;
import com.poker.login.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginServiceImpl {

    private final LoginRepository loginRepository;

    @Override
    public Integer save(LoginEntity login) {
        if (existsByEmail(login.getEmail())) {
            return 0;
        }
        loginRepository.save(login);
        return 1;
    }

    @Override
    public Integer login(LoginEntity login) {
        LoginEntity savedLogin = loginRepository.findByEmail(login.getEmail());
        if (login.getPassword().equals(savedLogin.getPassword())) {
            return 1;
        }
        return 0;
    }

    public Boolean existsByEmail(String email) {
        return loginRepository.existsByEmail(email);
    }
}

