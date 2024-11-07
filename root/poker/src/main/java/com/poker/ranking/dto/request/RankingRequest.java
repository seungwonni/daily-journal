package com.poker.ranking.dto.request;

import com.poker.login.dto.Login;
import com.poker.login.entity.LoginEntity;
import com.poker.ranking.entity.RankingEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
public class RankingRequest {
    private Integer tryCount;
    private String handRanking;
    private Double percent;

    public RankingEntity toEntity(Login login) {
        return RankingEntity.builder()
                .email(login.getEmail())
                .nickname(login.getNickname())
                .tryCount(this.tryCount)
                .handRanking(this.handRanking)
                .percent(this.percent)
                .build();
    }
}
