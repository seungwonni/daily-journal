package com.poker.ranking.service;

import com.poker.login.dto.Login;
import com.poker.login.entity.LoginEntity;
import com.poker.ranking.dto.request.RankingRequest;
import com.poker.ranking.entity.RankingEntity;
import com.poker.ranking.repository.RankingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingService implements RankingServiceImpl {

    private final RankingRepository rankingRepository;

    @Override
    public Integer save(RankingRequest request, Login login) {
        RankingEntity entity = request.toEntity(login);
        updateRanking(entity);
        return 1;
    }

    @Transactional
    protected void updateRanking(RankingEntity entity) {
        // 1. 새 항목이 들어갈 위치 찾기
        List<RankingEntity> currentTop100 = rankingRepository.findTop100ByHandRankingOrderByTryCountDescRankingAsc(entity.getHandRanking());
        int newRankIndex = -1;
        for (int i = 0; i < currentTop100.size(); i++) {
            RankingEntity ranking = currentTop100.get(i);
            // 새 항목의 `tryCount`가 더 크거나, 동점일 경우 ranking도 동일하게 처리
            if (ranking.getTryCount() < entity.getTryCount()) {
                newRankIndex = i;
                break;
            }
        }

        // 2. 새 항목 삽입할 위치 없으면 끝에 삽입
        if (newRankIndex == -1) {
            newRankIndex = currentTop100.size();
        }

        // 3. 기존 항목들의 순위를 한 칸씩 밀기
        rankingRepository.updateRanks(newRankIndex + 1);

        // 4. 새 RankingEntity 생성
        RankingEntity newRanking = new RankingEntity();
        newRanking.setEmail(entity.getEmail());
        newRanking.setTryCount(entity.getTryCount());
        newRanking.setHandRanking(entity.getHandRanking());
        newRanking.setRanking(newRankIndex + 1); // 순위는 1부터 시작
        newRanking.setNickname(entity.getNickname());
        newRanking.setPercent(entity.getPercent());


        // 5. 새 항목 삽입
        rankingRepository.save(newRanking);
        // 6. Top 100 유지 (순위 100 초과 항목 삭제)
        rankingRepository.deleteRanksAbove100();
    }
}

