package com.poker.controller.login;

import com.poker.dto.Login;
import com.poker.entity.LoginEntity;
import com.poker.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping(value = "/login")
    public String login(Login login) {
        return "";
    }

    @GetMapping(value = "/join")
    public ModelAndView join() {
        ModelAndView mnv = new ModelAndView();
        mnv.setViewName("/views/join");
        return mnv;
    }

    @PostMapping(value = "/join")
    @ResponseBody
    public Object join(@RequestBody Login login) {
        Map<String, Object> map = new HashMap<>();
        LoginEntity entity = new LoginEntity();
        entity.setPassword(login.getPassword());
        entity.setEmail(login.getEmail());
        entity.setNickname(login.getNickname());
        loginService.save(entity);
        return map;
    }
}
