package project_poker.dto;

import project_poker.service.HandRangeCalcStrategy;
import project_poker.util.MapCalcUtil;

import java.util.*;

public enum HandRanking {
    PROCESSING(null),
    ROYAL_STRAIGHT_FLUSH(null),
    STRAIGHT_FLUSH(null),

    FOUR_OF_A_KIND(null),
    FULL_HOUSE(null),
    FLUSH(null),
    STRAIGHT(null),
    THREE_OF_A_KIND(null),
    TWO_PAIR(null),
    ONE_PAIR(null),
    HIGH_CARD(null)
    ;
    private Integer value;
    HandRanking(Integer value) {
        this.value = value;
    }
    public Integer getValue() {
        return value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }
    public static HandRanking calcMaxValue(HandRanking handRanking, List<CardInfo> result) {
        Map<Integer, Integer> map = new HashMap();
        List<CardInfo> list = result;
        for (CardInfo cardInfo: list) {
            Integer count = map.get(cardInfo.getNumber());
            if (count == null) {
                map.put(cardInfo.getNumber(), 1);
            } else {
                map.put(cardInfo.getNumber(), count+1);
            }
        }
        Integer maxValue = Collections.max(map.values());
        handRanking.setValue(MapCalcUtil.getKey(map, maxValue));
        if (HandRanking.STRAIGHT_FLUSH == handRanking ||
                HandRanking.STRAIGHT == handRanking) {
            CardInfo.rollBackALetter(result);
        }
        return handRanking;
    }
}
