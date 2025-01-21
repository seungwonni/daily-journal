package com.rate.common.login.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rate.common.login.entity.LoginEntity;
import com.rate.common.login.dto.Login;
import com.rate.common.login.service.KakaoLoginService;
import com.rate.common.login.service.LoginService;
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
    private final KakaoLoginService kakaoLoginService;

    @PostMapping(value = "/login")
    @ResponseBody
    public Map<String, Object> login(@RequestBody Login login, HttpServletRequest request) {
        LoginEntity entity = login.toEntity();
        return loginService.login(entity, request);
    }

    @GetMapping(value = "/guest-login")
    @ResponseBody
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView mnv = new ModelAndView();
        HttpSession session = request.getSession();
        String tempId = ((long)(Math.random() * 2000000000)+ 1) + "A";
        session.setAttribute("userId", tempId);
        session.setAttribute("nickname", "게스트");
        session.setMaxInactiveInterval(18000);
        mnv.setViewName("views/mainChoice");
        return mnv;
    }

    @GetMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request) throws JsonProcessingException {
        ModelAndView mnv = new ModelAndView();
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        //오직 카카오톡 userId만
        if (userId.matches("[+-]?\\d*(\\.\\d+)?")) {
            kakaoLoginService.logout(request);
        }
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
