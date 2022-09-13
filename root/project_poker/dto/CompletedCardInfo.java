package dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompletedCardInfo {
    private List<PokerUserInfo> playerCard;
    //카드 리스트(총 52개)
    private List<CardInfo> cardList;
    //플레이어 공용으로 사용할 카드
    private List<CardInfo> board;
    private Integer players;
    private Map<String, Integer> ShadeNum;

    public List<PokerUserInfo> getPlayerCard() {
        return playerCard;
    }

    public void setPlayerCard(List<PokerUserInfo> playerCard) {
        this.playerCard = playerCard;
    }

    public List<CardInfo> getCardList() {
        return cardList;
    }

    public void setCardList(List<CardInfo> cardList) {
        this.cardList = cardList;
    }

    public List<CardInfo> getBoard() {
        return board;
    }

    public void setBoard(List<CardInfo> board) {
        this.board = board;
    }

    public Integer getPlayers() {
        return players;
    }

    public void setPlayers(Integer players) {
        this.players = players;
    }

    public Map<String, Integer> getMaxShade() {
        ShadeNum = new HashMap<>();
        ShadeNum.put("♦", 0);
        ShadeNum.put("♠", 0);
        ShadeNum.put("❤", 0);
        ShadeNum.put("♣", 0);
        return ShadeNum;
    }

    public CompletedCardInfo(List<PokerUserInfo> playerCard, List<CardInfo> cardList, List<CardInfo> board, Integer players) {
        this.playerCard = playerCard;
        this.cardList = cardList;
        this.board = board;
        this.players = players;
    }
}
