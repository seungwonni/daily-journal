package project_poker.dto;

public class PokerUserInfo {
    private CardInfo firstCard;
    private CardInfo secondCard;
    private HandRanking result;

    public CardInfo getFirstCard() {
        return firstCard;
    }

    public void setFirstCard(CardInfo firstCard) {
        this.firstCard = firstCard;
    }

    public CardInfo getSecondCard() {
        return secondCard;
    }

    public void setSecondCard(CardInfo secondCard) {
        this.secondCard = secondCard;
    }

    public HandRanking getResult() {
        return result;
    }

    public void setResult(HandRanking result) {
        this.result = result;
    }
}
