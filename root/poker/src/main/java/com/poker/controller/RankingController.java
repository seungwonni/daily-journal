package com.poker.controller;

import com.poker.dto.request.RankingRequest;
import com.poker.entity.RankingEntity;
import com.poker.service.RankingService;
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
    public Object WriteRanking(@RequestBody RankingRequest ranking) {
        RankingEntity ranking1 = new RankingEntity();
        rankingService.save(ranking1);
        return "";
    }
}
