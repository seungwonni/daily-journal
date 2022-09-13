package com.poker_project.view;

import com.poker_project.dto.CardInfo;
import com.poker_project.dto.CompletedCardInfo;
import com.poker_project.dto.HandRanking;
import com.poker_project.dto.PokerUserInfo;
import com.poker_project.service.CalculateResultService;

import java.util.List;

public class ShowResult {
    private CalculateResultService calculateResultService = new CalculateResultService();
    public void showResult(CompletedCardInfo cardInfo) {
        List<PokerUserInfo> playerCard = cardInfo.getPlayerCard();
        List<CardInfo> board = cardInfo.getBoard();
        for (int i = 0; i < playerCard.size();i++) {
            System.out.println((i+1)+" player\t" + playerCard.get(i).getFirstCard() + "\t" + playerCard.get(i).getSecondCard());
        }
        System.out.println("board");
        for (int i = 0; i < board.size();i++) {
            System.out.print(board.get(i)+ "\n");
        }
        System.out.println("\n\n");
        calculateResultService.calcResult(cardInfo);
        for (PokerUserInfo pokerUserInfo :cardInfo.getPlayerCard()) {
            System.out.println(pokerUserInfo.getResult().getValue()+ " "+pokerUserInfo.getResult());
            if (pokerUserInfo.getResult() == HandRanking.FLUSH) {
                System.out.println("FLUSH");
            }
        }
    }
}
