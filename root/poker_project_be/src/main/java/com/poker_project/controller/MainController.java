package com.poker_project.controller;

import com.poker_project.service.SettingCardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {
    @GetMapping(value = "/main.do")
    @ResponseBody
    public Object main() {
        Map<String, Object> result = new HashMap<>();
        return result;
    }

    public static void main(String[] args) {
        SettingCardService settingCardService = new SettingCardService();
        settingCardService.startGame();
    }
}
