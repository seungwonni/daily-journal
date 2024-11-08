package com.poker.ranking.dto.request;

import com.poker.login.dto.Login;
import com.poker.ranking.entity.RankingEntity;
import lombok.Getter;

@Getter
public class RankingRequest {
    private Integer tryCount;
    private String handRanking;
    private Double percent;

    public RankingEntity toEntity(Login login) {
        return RankingEntity.builder()
                .nickname(login.getNickname())
                .tryCount(this.tryCount)
                .handRanking(this.handRanking)
                .percent(this.percent)
                .build();
    }
}
