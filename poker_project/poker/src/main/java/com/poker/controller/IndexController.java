package com.poker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/home")
@RequiredArgsConstructor
public class IndexController {

    @GetMapping()
    public ModelAndView index() {
        ModelAndView mnv = new ModelAndView();
        mnv.setViewName("views/index");
        return mnv;
    }
}
