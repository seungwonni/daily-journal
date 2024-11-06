package com.poker.ranking.service;

import com.poker.ranking.entity.RankingEntity;
import com.poker.ranking.repository.RankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RankingService implements RankingServiceImpl {

    private final RankingRepository rankingRepository;

    @Override
    public Integer save(RankingEntity ranking) {
        rankingRepository.save(ranking);
        return 1;
    }
}

