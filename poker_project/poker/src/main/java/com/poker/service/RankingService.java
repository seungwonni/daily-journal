package com.poker.service;

import com.poker.entity.Ranking;
import com.poker.repository.RankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RankingService implements RankingServiceImpl {

    private final RankingRepository rankingRepository;

    @Override
    public Integer save(Ranking ranking) {
        rankingRepository.save(ranking);
        return 1;
    }
}

