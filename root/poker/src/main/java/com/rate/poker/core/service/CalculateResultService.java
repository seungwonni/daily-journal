package com.rate.poker.core.service;

import com.rate.poker.core.dto.CardInfo;
import com.rate.poker.core.dto.CompletedCardInfo;
import com.rate.poker.core.dto.HandRanking;
import com.rate.poker.core.dto.PokerUserInfo;

import java.util.List;

public class CalculateResultService {
    private List<PokerUserInfo> playerCards;
    private List<CardInfo> board;
    private StraightFlushCalcStrategy straightFlushCalcStrategy = new StraightFlushCalcStrategy();
    private HandRangeCalcStrategy handRangeCalcStrategy = new HandRangeCalcStrategy();

    public HandRanking calcResult(CompletedCardInfo cardInfo) {
        HandRanking handRanking = null;
        this.playerCards = cardInfo.getPlayerCard();
        this.board = cardInfo.getBoard();
        for (PokerUserInfo playerCard : playerCards) {
            playerCard.combineCardWithBoards(board);
            playerCard.setBoard(board);
            straightFlushCalcStrategy.calculate(cardInfo.getMaxShade(), playerCard);
            if (playerCard.getResult() != HandRanking.PROCESSING) {
                continue;
            }
            handRangeCalcStrategy.calculate(playerCard, board);
        }
        return handRanking;
    }
}
