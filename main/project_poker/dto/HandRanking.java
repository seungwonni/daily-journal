package project_poker.dto;

import java.util.List;

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
        Integer maxValue = result.stream().mapToInt(t->t.getNumber()).max().getAsInt();
        handRanking.setValue(maxValue);
        if (HandRanking.STRAIGHT_FLUSH == handRanking ||
                HandRanking.STRAIGHT == handRanking) {
            CardInfo.rollBackALetter(result);
        }
        return handRanking;
    }
}
