package project_poker.service;

import project_poker.dto.*;
import project_poker.view.ShowResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SettingCardService {
    //참여할 플레이어 갯수

    ShowResult view = new ShowResult();
    private final Integer players = 2;
    private List<PokerUserInfo> playerCard = new ArrayList<>();
    private List<CardInfo> cardList= new ArrayList<>();
    private List<CardInfo> board = new ArrayList<>();

    public void startGame() {
        init();
        setCard();
        dealCard();
        setFlop();
        setTurn();
        setLiver();
        view.showResult(setCardInfoObj());
    }
    private void init() {
        playerCard = new ArrayList<>();
        cardList = new ArrayList<>();
        board = new ArrayList<>();
    }

    private void setCard() {
        // 카드 셋팅
        for (CardInfo cardInfo : CardInfo.values()) {
            cardList.add(cardInfo);
        }
        // 카드 셔플
        Collections.shuffle(cardList);
    }
    //플레이어 갯수에 맞춰 카드를 나눠줌
    private void dealCard() {
        CardInfo card;
        PokerUserInfo userInfo;
        for (int i = 0; i < players; i++) {
            userInfo = new PokerUserInfo();
            card = cardList.get(i);
            userInfo.setFirstCard(card);
            playerCard.add(userInfo);
            cardList.remove(i);
        }

        for (int i = 0; i < players; i++) {
            card = cardList.get(i);
            playerCard.get(i).setSecondCard(card);
            cardList.remove(i);
        }
    }
    // 공통으로 3개를 까는 플랍
    // (첫번째 카드를 무조건 버리고 그 이후 카드 3개를 board 객체에 저장 )
    private void setFlop() {
        for (int i =0; i < 4; i++) {
            if (i == 0)
                cardList.remove(cardList.get(i));
            else
                board.add(cardList.get(i));cardList.remove(cardList.get(i));
        }
    }
    //첫카드는 버리고 보드 객체에 저장
    private void setTurn() {
        for (int i =0; i < 2; i++) {
            if (i == 0)
                cardList.remove(cardList.get(i));
            else
                board.add(cardList.get(i));cardList.remove(cardList.get(i));
        }
    }
    //첫카드는 버리고 보드 객체에 저장
    private void setLiver() {
        for (int i =0; i < 2; i++) {
            if (i == 0)
                cardList.remove(cardList.get(i));
            else
                board.add(cardList.get(i));cardList.remove(cardList.get(i));
        }
    }
    //계산이 완료된 카드 정보 객체 생성(화면단에 객체로 보내기위함)
    private CompletedCardInfo setCardInfoObj() {
        return new CompletedCardInfo(playerCard, cardList, board, players);
    }
}
