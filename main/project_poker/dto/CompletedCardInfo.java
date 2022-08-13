package project_poker.dto;

import java.util.List;

public class CompletedCardInfo {
    private List<PokerUserInfo> playerCard;
    //카드 리스트(총 52개)
    private List<String> cardList;
    //플레이어 공용으로 사용할 카드
    private List<String> board;
    private Integer players;

    public List<PokerUserInfo> getPlayerCard() {
        return playerCard;
    }

    public List<String> getCardList() {
        return cardList;
    }

    public List<String> getBoard() {
        return board;
    }

    public Integer getPlayers() {
        return players;
    }

    public CompletedCardInfo(List<PokerUserInfo> playerCard, List<String> cardList, List<String> board, Integer players) {
        this.playerCard = playerCard;
        this.cardList = cardList;
        this.board = board;
        this.players = players;
    }
}
