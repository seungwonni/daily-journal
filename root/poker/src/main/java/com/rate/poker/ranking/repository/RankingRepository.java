package com.rate.poker.core.ranking.repository;


import com.rate.poker.core.ranking.entity.RankingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<RankingEntity, Integer> {

    List<RankingEntity> findTop100ByHandRankingOrderByTryCountAsc(String handRanking);

}
