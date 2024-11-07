package com.poker.ranking.service;

import com.poker.login.dto.Login;
import com.poker.ranking.dto.request.RankingRequest;

public interface RankingServiceImpl {

    Integer save(RankingRequest ranking, Login login);

}