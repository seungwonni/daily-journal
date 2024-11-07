package com.poker.ranking.controller;

import com.poker.login.dto.Login;
import com.poker.ranking.entity.RankingEntity;
import com.poker.ranking.dto.request.RankingRequest;
import com.poker.ranking.service.RankingService;
import com.poker.util.UserInfoUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @PostMapping(value = "/save")
    public Object WriteRanking(@RequestBody RankingRequest ranking, HttpServletRequest request) {
        Login login = UserInfoUtil.getUserInfo(request);
        rankingService.save(ranking, login);
        return "";
    }
}
