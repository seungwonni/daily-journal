package com.rate.common.login.service;

import com.rate.common.login.entity.LoginEntity;
import com.rate.common.login.repository.LoginRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
    public Map<String, Object> login(LoginEntity login, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        LoginEntity savedLogin = loginRepository.findByEmail(login.getEmail());
        if (login.getPassword().equals(savedLogin.getPassword())) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(18000);
            session.setAttribute("userId", savedLogin.getEmail());
            session.setAttribute("nickname", savedLogin.getNickname());
            result.put("result", "1");
            result.put("data", savedLogin);
        } else
            result.put("result", "0");
        return result;
    }

    public Boolean existsByEmail(String email) {
        return loginRepository.existsByEmail(email);
    }
}

