package com.poker.ranking.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankingRequest {
    private Integer count;
    private String result;
    private Double percent;
}
