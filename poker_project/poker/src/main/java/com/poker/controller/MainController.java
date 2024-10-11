package com.poker.controller;

import com.poker.dto.CompletedCardInfo;
import com.poker.service.SettingCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping(value = "/show-result")
    public ModelAndView showResult() {
        ModelAndView mnv = new ModelAndView();
        Integer playerNum = 8;
        CompletedCardInfo cardInfo = settingCardService.setCard(playerNum);

        mnv.addObject("board", cardInfo.getBoard());
        mnv.addObject("myCard", cardInfo.getPlayerCard());
        mnv.setViewName("/index");
        return mnv;
    }

    public static void main(String[] args) {
        SettingCardService settingCardService = new SettingCardService();
        settingCardService.startGame();
    }
}
