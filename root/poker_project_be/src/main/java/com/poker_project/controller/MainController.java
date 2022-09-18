package com.poker_project.controller;

import com.poker_project.service.SettingCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/poker/home")
@RequiredArgsConstructor
@CrossOrigin( allowedHeaders = "*")
public class MainController {
    private final SettingCardService settingCardService;
    @GetMapping(value = "/set-card")
    @ResponseBody
    public Map<String, Object> main(Integer playerNum) {
        Map<String, Object> result = new HashMap<>();
        result.put("result", settingCardService.get(playerNum));
        return result;
    }

    public static void main(String[] args) {
        SettingCardService settingCardService = new SettingCardService();
        settingCardService.startGame();
    }
}
