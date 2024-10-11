package com.poker.dto;

import java.util.ArrayList;
import java.util.List;

public class PokerUserInfo {
    private CardInfo firstCard;
    private CardInfo secondCard;
    private List<CardInfo> combinedCard;
    private HandRanking result;
    private List<CardInfo> board;

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

    public List<CardInfo> getCombinedCard() {
        return combinedCard;
    }

    public void combineCardWithBoards(List<CardInfo> board) {
        List<CardInfo> result = new ArrayList<>();
        result.addAll(board);
        result.add(this.getFirstCard());
        result.add(this.getSecondCard());
        this.combinedCard = result;
    }

    public HandRanking getResult() {
        return result;
    }

    public void setResult(HandRanking result) {
        this.result = result;
    }

    public List<CardInfo> getBoard() {
        return board;
    }

    public void setBoard(List<CardInfo> board) {
        this.board = board;
    }
}
