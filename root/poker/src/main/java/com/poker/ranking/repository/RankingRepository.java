package com.poker.ranking.repository;


import com.poker.ranking.entity.RankingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<RankingEntity, Integer> {

    List<RankingEntity> findTop100ByHandRankingOrderByTryCountAsc(String handRanking);

}
