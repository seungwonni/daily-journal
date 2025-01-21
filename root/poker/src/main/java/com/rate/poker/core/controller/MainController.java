package com.rate.poker.core.controller;

import com.rate.poker.core.dto.CompletedCardInfo;
import com.rate.poker.core.service.SettingCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/main")
@RequiredArgsConstructor
@CrossOrigin( allowedHeaders = "*")
public class MainController {
    private final SettingCardService settingCardService;

    @GetMapping(value = "/show-result")
    public ModelAndView showResult(String requestResult) {
        ModelAndView mnv = new ModelAndView();
        CompletedCardInfo cardInfo = settingCardService.startProcess(1);
        mnv.addObject("board", cardInfo.getBoard());
        mnv.addObject("myCard", cardInfo.getPlayerCard());
        mnv.addObject("requestResult", requestResult);
        mnv.setViewName("/views/showResult");
        return mnv;
    }

    @GetMapping(value = "/show-bulk")
    public ModelAndView showBulk(String requestResult) {
        ModelAndView mnv = new ModelAndView();
        mnv.addObject("requestResult", requestResult);
        mnv.setViewName("/views/showBulk");
        return mnv;
    }

    @PostMapping(value="/retry")
    @ResponseBody
    public Object retry() {
        Map result = new HashMap();
        CompletedCardInfo cardInfo = settingCardService.startProcess(1);
        result.put("board", cardInfo.getBoard());
        result.put("myCard", cardInfo.getPlayerCard());
        return result;
    }

    @PostMapping(value="/retry-bulk")
    @ResponseBody
    public Object retryBulk(@RequestBody Map<String, String> request) {
        return settingCardService.startBulkProcess(request.get("requestResult"));
    }
}
