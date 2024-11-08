package com.poker.login.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.poker.login.service.KakaoLoginService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequiredArgsConstructor
public class KakaoLoginController {

    private final KakaoLoginService loginService;

    @GetMapping(value = "/kakao-login")
    public ModelAndView login(@RequestParam("code") String authorizationCode, HttpServletRequest request) throws JsonProcessingException {
        ModelAndView mnv = new ModelAndView();
        loginService.login(authorizationCode, request);
        mnv.setViewName("views/mainChoice");
        return mnv;
    }
}
