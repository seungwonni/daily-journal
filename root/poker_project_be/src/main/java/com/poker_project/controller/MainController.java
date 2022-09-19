package com.poker_project.controller;

import com.poker_project.service.SettingCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public Map<String, Object> setCard(Integer playerNum) {
        Map<String, Object> result = new HashMap<>();
        result.put("result", settingCardService.setCard(playerNum));
        return result;
    }

    public static void main(String[] args) {
        SettingCardService settingCardService = new SettingCardService();
        settingCardService.startGame();
    }
}
