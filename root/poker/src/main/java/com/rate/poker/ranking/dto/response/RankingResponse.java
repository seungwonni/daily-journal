package com.rate.poker.core.ranking.dto.response;

import com.rate.poker.core.ranking.entity.RankingEntity;
import lombok.Getter;

@Getter
public class RankingResponse {
    private Integer rankNum;
    //Login 객체에서 셋팅
    private String email;
    private String nickname;
    //RankingRequest 객체에서 셋팅
    private Integer tryCount;
    private String handRanking;
    private Double percent;
    private Integer ranking;

    public RankingResponse setResponse(RankingEntity entity, Integer ranking) {
        RankingResponse response = new RankingResponse();
        response.rankNum = entity.getRankNum();
        response.nickname = entity.getNickname();
        response.tryCount = entity.getTryCount();
        response.handRanking = entity.getHandRanking();
        response.percent = entity.getPercent();
        response.ranking = ranking;
        return response;
    }
}
