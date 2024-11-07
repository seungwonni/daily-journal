package com.poker.login.controller;

import com.poker.login.entity.LoginEntity;
import com.poker.login.dto.Login;
import com.poker.login.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/common")
public class LoginController {

    private final LoginService loginService;

    @PostMapping(value = "/login")
    @ResponseBody
    public Map<String, Object> login(@RequestBody Login login, HttpServletRequest request) {
        LoginEntity entity = login.toEntity();
        return loginService.login(entity, request);
    }

    @GetMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request) {
        ModelAndView mnv = new ModelAndView();
        HttpSession session = request.getSession();
        session.invalidate();
        mnv.setViewName("views/index");
        return mnv;
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
        Map<String, Object> result = new HashMap<>();
        LoginEntity entity = login.toEntity();
        result.put("result", loginService.save(entity));
        return result;
    }
}
