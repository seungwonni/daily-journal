package com.rate.poker.ranking.controller;

import com.rate.common.login.dto.Login;
import com.rate.poker.core.ranking.dto.request.RankingRequest;
import com.rate.common.login.util.UserInfoUtil;
import com.rate.poker.ranking.service.RankingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/ranking")
@RequiredArgsConstructor
@CrossOrigin( allowedHeaders = "*")
public class RankingController {

    private final RankingService rankingService;

    @GetMapping()
    public ModelAndView showRanking() {
        ModelAndView mnv = new ModelAndView();
        mnv.setViewName("views/ranking");
        return mnv;
    }

    @GetMapping("/list")
    @ResponseBody
    public Map<String, Object> getRanking(@RequestParam String handRanking) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", rankingService.getTopRankingList(handRanking));
        return result;
    }

    @PostMapping(value = "/save")
    public Object save(@RequestBody RankingRequest ranking, HttpServletRequest request) {
        Login login = UserInfoUtil.getUserInfo(request);
        return rankingService.save(ranking, login);
    }
}
