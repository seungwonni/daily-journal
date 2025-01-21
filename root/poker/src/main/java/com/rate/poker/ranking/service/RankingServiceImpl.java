package com.rate.poker.ranking.service;

import com.rate.common.login.dto.Login;
import com.rate.poker.core.ranking.dto.request.RankingRequest;

public interface RankingServiceImpl {

    Integer save(RankingRequest ranking, Login login);

}