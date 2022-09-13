package service;

import project_poker.dto.CardInfo;
import project_poker.dto.CompletedCardInfo;
import project_poker.dto.HandRanking;
import project_poker.dto.PokerUserInfo;

import java.util.*;

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
