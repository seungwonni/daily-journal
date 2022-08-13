package project_poker.view;

import project_poker.dto.CompletedCardInfo;
import project_poker.dto.PokerUserInfo;

import java.util.List;

public class ShowResult {
    public void showResult(CompletedCardInfo cardInfo) {
        List<PokerUserInfo> playerCard = cardInfo.getPlayerCard();
        List<String> board = cardInfo.getBoard();
        for (int i = 0; i < playerCard.size();i++) {
            System.out.println((i+1)+"번째 플레이어 : " + playerCard.get(i).getFirstCard() + "\t" + playerCard.get(i).getSecondCard());
        }
        System.out.println("보드");
        for (int i = 0; i < board.size();i++) {
            System.out.print(board.get(i)+ "\n");
        }
        System.out.println("\n\n");
    }
}
