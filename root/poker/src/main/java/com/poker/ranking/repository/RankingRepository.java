package com.poker.ranking.repository;


import com.poker.ranking.entity.RankingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<RankingEntity, Integer> {

    List<RankingEntity> findTop100ByHandRankingOrderByTryCountDescRankingAsc(String handRanking);

    // 순위가 특정 값 이상인 모든 엔티티를 업데이트
    @Modifying
    @Query("UPDATE RankingEntity r SET r.ranking = r.ranking + 1 WHERE r.ranking >= :ranking")
    @Transactional
    void updateRanks(@Param("ranking") Integer ranking);

    // 순위를 100보다 초과하는 모든 항목 삭제
    @Modifying
    @Query("DELETE FROM RankingEntity r WHERE r.ranking > 100")
    @Transactional
    void deleteRanksAbove100();
}
