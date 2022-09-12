package project_poker.dto;

import java.util.List;

public enum CardInfo {
    /**
     * 다이아 카드
    * */

    TWO_DIA("2", "♦", 2),
    THREE_DIA("3", "♦",3),
    FOUR_DIA("4", "♦",4),
    FIVE_DIA("5", "♦",5),
    SIX_DIA("6", "♦",6),
    SEVEN_DIA("7", "♦",7),
    EIGHT_DIA("8", "♦",8),
    NINE_DIA("9", "♦",9),
    TEN_DIA("10", "♦",10),
    JACK_DIA("J", "♦",11),
    QUEEN_DIA("Q", "♦",12),
    KING_DIA("K", "♦",13),
    ACE_DIA("A", "♦", 14),
    /**
     * 스페이드 카드
     * */

    TWO_SPADE("2", "♠",2),
    THREE_SPADE("3", "♠",3),
    FOUR_SPADE("4", "♠",4),
    FIVE_SPADE("5", "♠",5),
    SIX_SPADE("6", "♠",6),
    SEVEN_SPADE("7", "♠",7),
    EIGHT_SPADE("8", "♠",8),
    NINE_SPADE("9", "♠",9),
    TEN_SPADE("10", "♠",10),
    JACK_SPADE("J", "♠",11),
    QUEEN_SPADE("Q", "♠",12),
    KING_SPADE("K", "♠",13),
    ACE_SPADE("A", "♠",14),
    /**
     * 하트 카드
     * */
    TWO_HEART("2", "❤",2),
    THREE_HEART("3", "❤",3),
    FOUR_HEART("4", "❤",4),
    FIVE_HEART("5", "❤",5),
    SIX_HEART("6", "❤",6),
    SEVEN_HEART("7", "❤",7),
    EIGHT_HEART("8", "❤",8),
    NINE_HEART("9", "❤",9),
    TEN_HEART("10", "❤",10),
    JACK_HEART("J", "❤",11),
    QUEEN_HEART("Q", "❤",12),
    KING_HEART("K", "❤",13),
    ACE_HEART("1", "❤",14),
    /**
     * 클로버 카드
     * */
    TWO_CLUB("2", "♣",2),
    THREE_CLUB("3", "♣",3),
    FOUR_CLUB("4", "♣",4),
    FIVE_CLUB("5", "♣",5),
    SIX_CLUB("6", "♣",6),
    SEVEN_CLUB("7", "♣",7),
    EIGHT_CLUB("8", "♣",8),
    NINE_CLUB("9", "♣",9),
    TEN_CLUB("10", "♣",10),
    JACK_CLUB("J", "♣",11),
    QUEEN_CLUB("Q", "♣",12),
    KING_CLUB("K", "♣",13),
    ACE_CLUB("1", "♣",14)
    ;
    private final String name;
    private final String suit;
    private Integer number;

    CardInfo(String name, String suit, Integer number) {
        this.name = name;
        this.suit = suit;
        this.number = number;
    }

    public String getName() {
        return name;
    }
    public String getSuit() {return suit;}

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public static void convertALetter(CardInfo cardInfo) {
        cardInfo.setNumber(1);
    }
    public static void rollBackALetter(List<CardInfo> cardInfos) {
        if (cardInfos.stream().anyMatch(t->t.getNumber() == 1)) {
            CardInfo info = cardInfos.stream().filter(t->t.getNumber() == 1)
                    .findAny().get();
            info.setNumber(14);
        }
    }
}
