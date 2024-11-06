package com.poker.login.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.poker.login.service.KakaoLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class KakaoLoginController {

    private final KakaoLoginService loginService;

    @GetMapping(value = "/kakao-login")
    public ModelAndView login(@RequestParam("code") String authorizationCode) throws JsonProcessingException {
        ModelAndView mnv = new ModelAndView();
        mnv.addObject("type", "kakao");
        mnv.addObject("nickname", loginService.login(authorizationCode));
        mnv.setViewName("views/mainChoice");
        return mnv;
    }
}
