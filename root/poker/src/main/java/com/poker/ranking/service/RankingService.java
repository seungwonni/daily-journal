package com.poker.ranking.service;

import com.poker.login.dto.Login;
import com.poker.ranking.dto.request.RankingRequest;
import com.poker.ranking.dto.response.RankingResponse;
import com.poker.ranking.entity.RankingEntity;
import com.poker.ranking.repository.RankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingService implements RankingServiceImpl {

    private final RankingRepository rankingRepository;

    @Override
    public Integer save(RankingRequest request, Login login) {
        RankingEntity entity = request.toEntity(login);
        // 1. 새 항목 삽입
        rankingRepository.save(entity);
        return 1;
    }

    public List<RankingResponse> getTopRankingList(String handRanking) {
        // tryCount 기준 내림차순으로 정렬된 리스트 가져오기
        List<RankingEntity> sortedRankings = rankingRepository.findTop100ByHandRankingOrderByTryCountAsc(handRanking);

        List<RankingResponse> rankingResponses = new ArrayList<>();
        int currentRank = 1;  // 순위 시작
        int sameRankCount = 1; // 공동 순위 수

        for (int i = 0; i < sortedRankings.size(); i++) {
            RankingEntity entity = sortedRankings.get(i);

            // 첫 번째 항목은 1등으로 설정
            if (i == 0) {
                currentRank = 1;
            } else if (entity.getTryCount() == sortedRankings.get(i - 1).getTryCount()) {
                // 이전 항목과 같은 tryCount인 경우 공동 순위 처리
                sameRankCount++;
            } else {
                // 이전 항목과 다른 tryCount인 경우, 다음 순위로 업데이트
                currentRank += sameRankCount;
                sameRankCount = 1; // 새로운 순위 집합 시작
            }

            // RankingResponse에 데이터 넣기
            RankingResponse response = new RankingResponse().setResponse(entity , currentRank);
            rankingResponses.add(response);
        }
        return rankingResponses;
    }
}

