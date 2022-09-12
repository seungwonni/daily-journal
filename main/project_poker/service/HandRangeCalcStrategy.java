package project_poker.service;

import project_poker.dto.CardInfo;
import project_poker.dto.HandRanking;
import project_poker.dto.PokerUserInfo;

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
        handRanking = calcFullHouse(combinedCard);
        if (handRanking != HandRanking.PROCESSING) {
            if (handRanking == HandRanking.THREE_OF_A_KIND) {
                playerCard.setResult(handRanking);
                return;
            }
            playerCard.combineCardWithBoards(board);
        }
    }
    private Map<Integer, Integer> calcMaxNumber(List<CardInfo> cardInfos) {
        Map<Integer, Integer> result = new HashMap<>();
        Integer maxValue = cardInfos.stream().mapToInt(x->x.getNumber()).max().getAsInt();
        Long maxNumber = cardInfos.stream().filter(t -> t.getNumber() == maxValue).count();
        result.put(maxValue, maxNumber.intValue());
        return result;
    }
    private HandRanking calcFourOfAKind(List<CardInfo> combinedCard) {
        Integer maxValue = 0;
        for (Integer value : calcMaxNumber(combinedCard).values()) {
            maxValue = value;
        }
        return maxValue == 4 ? HandRanking.calcMaxValue(HandRanking.FOUR_OF_A_KIND, combinedCard) : HandRanking.PROCESSING;
    }
    private HandRanking calcFullHouse(List<CardInfo> combinedCard) {
        List<CardInfo> list = combinedCard;
        Map<Integer, Integer> result = calcMaxNumber(list);
        boolean fullHouseFlag;
        for (Integer key : result.keySet()) {
            if (result.get(key) == 3) {
                fullHouseFlag = isIncludeOnePairWithoutTriple(list, key);
                return fullHouseFlag ?
                        HandRanking.calcMaxValue(HandRanking.FULL_HOUSE, combinedCard) :
                        HandRanking.calcMaxValue(HandRanking.THREE_OF_A_KIND, combinedCard);
            }
        }
        return HandRanking.PROCESSING;
    }
    private boolean isIncludeOnePairWithoutTriple(List<CardInfo> list, Integer tripleValue) {
        list.removeIf(t-> t.getNumber() == (tripleValue));
        Map<Integer, Integer> result = calcMaxNumber(list);
        Integer maxValue = 0;
        for (Integer value : result.values()) {
            maxValue = value;
        }
        return maxValue == 2 ? true : false;
    }
    private HandRanking calcTwoPair(List<CardInfo> combinedCard) {
        Map<Integer, Integer> result = calcMaxNumber(combinedCard);
        for (Integer value : result.values()) {
            maxValue = value;
        }
        return null;
    }
}
