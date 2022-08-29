package project_poker.service;

import project_poker.dto.CardInfo;
import project_poker.dto.CompletedCardInfo;
import project_poker.dto.HandRanking;
import project_poker.dto.PokerUserInfo;

import java.util.*;

public class CalculateResultService {
    private List<PokerUserInfo> playerCards;
    private List<CardInfo> board;
    private Map.Entry<String, Integer> maxShade;
    public void calcResult(CompletedCardInfo cardInfo) {
        this.playerCards = cardInfo.getPlayerCard();
        this.board = cardInfo.getBoard();
        boolean isPossibleFlush = IsPossibleFlush(cardInfo.getMaxShade());
        for (PokerUserInfo playerCard : playerCards) {
            List<CardInfo> combinedCard =  combineCardWithBoards(playerCard);
            if (isPossibleFlush) {
                calcRoyalStraightFlush(combinedCard);
                calcStraightFlush(combinedCard);
            }
        }
    }
    private boolean IsPossibleFlush(Map<String, Integer> shadeNum) {
        List<String> shades = Arrays.asList("♦", "♠", "❤", "♣");
        for (CardInfo cardInfo : board) {
            for (String shade : shades) {
                if (shade.equals(cardInfo.getSuit())) {
                    Integer shadeCount = shadeNum.get(shade);
                    shadeNum.put(shade, shadeCount+1);
                    break;
                }
            }
        }
        maxShade = shadeNum.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .orElse(null);
        return maxShade.getValue() >= 3 ? true : false;
    }
    private List<CardInfo> combineCardWithBoards(PokerUserInfo playerCard) {
        List<CardInfo> result = new ArrayList<>();
        result.addAll(board);
        result.add(playerCard.getFirstCard());
        result.add(playerCard.getSecondCard());
        return result;
    }
    private HandRanking calcRoyalStraightFlush(List<CardInfo> result) {
        // 플러쉬 가능성 없는 카드는 제거
        result.removeIf(t -> !t.getSuit().equals(maxShade.getKey()));
        // 플러쉬 갯수 5개 미만임 (플러쉬 관련 카드는 아닌걸로 판별)
        if (result.size() < 5)
            return HandRanking.PROCESSING;
        // 로얄 플러쉬는 A가 항상 있어야 함으로 'A' 존재 여부를 판단(로얄 스티플은 아니지만 최소 플러쉬 가능성이 있음)
        if (result.stream().filter(t -> t.getNumber() == 14).count() == 0)
            return HandRanking.FLUSH;
        Integer[] royalNum = {14, 13, 12, 11, 10};
        // royalNum 객체와 매칭 여부 판단
        if (result.stream().filter(t->t.getNumber().equals(royalNum)).count() == 5)
            return HandRanking.FLUSH;
        return HandRanking.ROYAL_STRAIGHT_FLUSH;
    }
    private HandRanking calcStraightFlush(List<CardInfo> result) {
        result.removeIf(t -> !t.getSuit().equals(maxShade.getKey()));
        // 플러쉬 갯수 5개 미만임 (플러쉬 관련 카드는 아닌걸로 판별)
        if (result.size() < 5)
            return HandRanking.PROCESSING;
        // 로티플에 A의 값은 14로 셋팅을 하는 데 스티플은 A의 값을 1로 내려버려서 계산식을 유용하도록 설정
        if (result.stream().anyMatch(t-> t.getNumber() == 14)) {
            CardInfo info = result.stream().filter(t->t.getNumber() == 14)
                            .findAny().get();
            CardInfo.convertALetter(info);
        }

        result.sort((a, b) -> b.getNumber() - a.getNumber());
        Integer sfCount = result.size();
        Integer calcNum = Collections.max(result).getNumber();
        Integer currNum;
        Integer connectedCount = 1;
        for (int i = 0 ; i< result.size();i++) {
            currNum = result.get(i).getNumber();
            if (calcNum != currNum) {
                    --sfCount;
                    calcNum = currNum - 1;
                    connectedCount = 1;
            } else if (calcNum == currNum) {
                    --calcNum;
                    ++connectedCount;
            }
            if (sfCount < 5) {
                return HandRanking.FLUSH;
            }
        }
        return connectedCount >= 5 ? HandRanking.STRAIGHT_FLUSH : HandRanking.FLUSH;
    }
}
