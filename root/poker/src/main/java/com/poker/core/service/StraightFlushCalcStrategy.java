package com.poker.core.service;

import com.poker.core.dto.CardInfo;
import com.poker.core.dto.HandRanking;
import com.poker.core.dto.PokerUserInfo;

import java.util.*;

public class StraightFlushCalcStrategy {
    private Map.Entry<String, Integer> maxShade;
    protected void calculate(Map<String, Integer> maxShade , PokerUserInfo playerCard) {
        HandRanking handRanking;
        List<CardInfo> combinedCard = playerCard.getCombinedCard();
        handRanking = calcFlush(maxShade, combinedCard);
        if (handRanking == HandRanking.FLUSH) {
            trimOtherShade(combinedCard);
            handRanking = calcRoyalStraightFlush(combinedCard);
            if (handRanking == HandRanking.ROYAL_STRAIGHT_FLUSH) {
                playerCard.setResult(handRanking);
                return;
            }
            handRanking = calcStraightFlush(combinedCard);
            if (handRanking == HandRanking.STRAIGHT_FLUSH) {
                playerCard.setResult(handRanking);
                return;
            }
        } else {
            handRanking = calcStraight(combinedCard);
            if (handRanking == HandRanking.STRAIGHT) {
                playerCard.setResult(handRanking);
                return;
            }
        }

        playerCard.setResult(handRanking);
    }
    private HandRanking calcFlush(Map<String, Integer> shadeNum, List<CardInfo> combinedCard) {
        List<String> shades = Arrays.asList("♦", "♠", "❤", "♣");
        for (CardInfo cardInfo : combinedCard) {
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
        return maxShade.getValue() >= 5 ? HandRanking.calcMaxValue(HandRanking.FLUSH, combinedCard) : HandRanking.PROCESSING;
    }
    private HandRanking calcRoyalStraightFlush(final List<CardInfo> result) {
        // 로얄 플러쉬는 A가 항상 있어야 함으로 'A' 존재 여부를 판단(로얄 스티플은 아니지만 최소 플러쉬 가능성이 있음)
        if (result.stream().filter(t -> t.getNumber() == 14).count() == 0)
            return HandRanking.FLUSH;
        Integer[] royalNums = {14, 13, 12, 11, 10};
        Integer royalCount = 0;
        // royalNum 객체와 매칭 여부 판단
        for (CardInfo cardInfo : result) {
            for (Integer royalNum : royalNums) {
                if (cardInfo.getNumber() == royalNum) {
                    ++royalCount;
                    break;
                }
            }
        }
        return royalCount == 5 ? HandRanking.ROYAL_STRAIGHT_FLUSH : HandRanking.FLUSH;
    }
    protected HandRanking calcStraight(List<CardInfo> combinedCard) {
        CardInfo info = null;
        // 로티플에 A의 값은 14로 셋팅을 하는 데 스티플은 A의 값을 1로 내려버려서 계산식을 유용하도록 설정
        if (combinedCard.stream().anyMatch(t-> t.getNumber() == 14)) {
            info = combinedCard.stream().filter(t->t.getNumber() == 14)
                    .findAny().get();
            CardInfo.convertALetter(info);
        }
        combinedCard.sort((a, b) -> b.getNumber() - a.getNumber());
        Integer sfCount = combinedCard.size();
        Integer calcNum = Collections.max(combinedCard).getNumber();
        Integer currNum;
        Integer connectedCount = 1;
        for (int i = 0 ; i< combinedCard.size();i++) {
            currNum = combinedCard.get(i).getNumber();
            if (calcNum != currNum) {
                --sfCount;
                calcNum = currNum - 1;
                connectedCount = 1;
            } else if (calcNum == currNum) {
                --calcNum;
                ++connectedCount;
            }
            if (sfCount < 5) {
                return HandRanking.PROCESSING;
            }
        }
        return connectedCount >= 5 ? HandRanking.calcMaxValue(HandRanking.STRAIGHT, combinedCard) : HandRanking.PROCESSING;
    }
    protected HandRanking calcStraightFlush(List<CardInfo> result) {
        HandRanking handRanking = calcStraight(result);
        return handRanking == HandRanking.STRAIGHT ? HandRanking.calcMaxValue(HandRanking.STRAIGHT_FLUSH, result) : HandRanking.FLUSH;
    }
    private void trimOtherShade(List<CardInfo> combinedCard) {
        // 플러쉬 가능성 없는 카드는 제거
        combinedCard.removeIf(t -> !t.getSuit().equals(maxShade.getKey()));
    }

}
