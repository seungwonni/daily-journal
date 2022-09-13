package com.poker_project.service;

import com.poker_project.util.MapCalcUtil;
import com.poker_project.dto.CardInfo;
import com.poker_project.dto.HandRanking;
import com.poker_project.dto.PokerUserInfo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandRangeCalcStrategy {
    protected void calculate(PokerUserInfo playerCard, List<CardInfo> board) {
        List<CardInfo> combinedCard = playerCard.getCombinedCard();
        HandRanking handRanking;
        handRanking = calcFourOfAKind(combinedCard);
        if (handRanking == HandRanking.FOUR_OF_A_KIND) {
            playerCard.setResult(handRanking);
            return;
        }
        handRanking = calcFullHouse(playerCard);
        if (handRanking != null) {
                playerCard.setResult(handRanking);
                return;
        }
        handRanking = calcPairCard(playerCard);
        playerCard.setResult(handRanking);
    }
    private Map<Integer, Integer> calcMaxNumber(List<CardInfo> cardInfos) {
        Map<Integer, Integer> result = new HashMap<>();
        for (CardInfo cardInfo : cardInfos) {
            Integer count = result.get(cardInfo.getNumber());
            if (count == null) {
                result.put(cardInfo.getNumber(), 1);
            } else {
                result.put(cardInfo.getNumber(), count+1);
            }
        }
        return result;
    }

    private HandRanking calcFourOfAKind(List<CardInfo> combinedCard) {
        Integer maxValue = Collections.max(calcMaxNumber(combinedCard).values());
        return maxValue == 4 ? HandRanking.calcMaxValue(HandRanking.FOUR_OF_A_KIND, combinedCard) : HandRanking.PROCESSING;
    }
    private HandRanking calcFullHouse(PokerUserInfo playerCard) {
        List<CardInfo> combinedCard = playerCard.getCombinedCard();
        Map<Integer, Integer> result = calcMaxNumber(combinedCard);

        Integer maxValue = Collections.max(result.values());
        Integer maxKey = MapCalcUtil.getKey(result, maxValue);
        HandRanking handRanking = null;
        boolean fullHouseFlag;
        if (maxValue == 3) {
                fullHouseFlag = isIncludeOnePairWithoutValue(playerCard, maxKey);
                handRanking = fullHouseFlag ?
                        HandRanking.calcMaxValue(HandRanking.FULL_HOUSE, combinedCard) :
                        HandRanking.calcMaxValue(HandRanking.THREE_OF_A_KIND, combinedCard);
        }

        return handRanking;
    }
    /**
     * 풀하우스와 투페어는 기존에 원페어/트리플 이외에 값을 제외하고
     * 원페어가 있어야 풀하우스 및 투페어가 만들어진다.
     * 그래서 기존에 값을 제외하고 원페어 있는지 여부를 판단하는 메소드
    * */
    private boolean isIncludeOnePairWithoutValue(PokerUserInfo playerCard, Integer preValue) {
        List<CardInfo> combinedCard = playerCard.getCombinedCard();
        combinedCard.removeIf(t-> t.getNumber() == (preValue));
        Map<Integer, Integer> result = calcMaxNumber(combinedCard);
        Integer maxValue = Collections.max(result.values());
        //객체 초기화
        playerCard.combineCardWithBoards(playerCard.getBoard());
        return maxValue == 2 ? true : false;
    }
    private HandRanking calcPairCard(PokerUserInfo playerCard) {
        List<CardInfo> combinedCard = playerCard.getCombinedCard();
        Map<Integer, Integer> result = calcMaxNumber(combinedCard);
        HandRanking handRanking;
        Integer maxValue = Collections.max(result.values());
        Integer maxKey = MapCalcUtil.getKey(result, maxValue);

            if (maxValue == 1) {
                handRanking =  HandRanking.calcMaxValue(HandRanking.HIGH_CARD, combinedCard);
            } else {
                boolean twoPairFlag = isIncludeOnePairWithoutValue(playerCard, maxKey);
                handRanking = twoPairFlag ?
                        HandRanking.calcMaxValue(HandRanking.TWO_PAIR, combinedCard) :
                        HandRanking.calcMaxValue(HandRanking.ONE_PAIR, combinedCard);
            }
        return handRanking;
    }
}
