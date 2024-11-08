package com.poker.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class MainChoiceController {

    @GetMapping(value = "/home")
    public ModelAndView mainChoice(String type) {
        ModelAndView mnv = new ModelAndView();
        mnv.setViewName("views/mainChoice");
        return mnv;
    }

    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mnv = new ModelAndView();
        mnv.setViewName("views/index");
        return mnv;
    }
}
