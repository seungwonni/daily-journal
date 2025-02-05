package com.rate.poker.core.service;

import com.rate.poker.core.dto.CardInfo;
import com.rate.poker.core.dto.CompletedCardInfo;
import com.rate.poker.core.dto.PokerUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SettingCardService {
    //참여할 플레이어 갯수
    private Integer players = 0;
    private List<PokerUserInfo> playerCard = new ArrayList<>();
    private List<CardInfo> cardList= new ArrayList<>();
    private List<CardInfo> board = new ArrayList<>();

    private final CalculateResultService calculateResultService = new CalculateResultService();

    public CompletedCardInfo startProcess(Integer playerNum) {
        players = playerNum;
        init();
        setCard();
        dealCard();
        setFlop();
        setTurn();
        setLiver();
        calculateResultService.calcResult(setCardInfoObj());
        return setCardInfoObj();
    }

    public Map startBulkProcess(String requestResult) {
        players = 1;
        Integer count = 0;
        Map<String, Object> result = new HashMap<>();
        while (true) {
            init();
            setCard();
            dealCard();
            setFlop();
            setTurn();
            setLiver();
            calculateResultService.calcResult(setCardInfoObj());
            ++count;
            if (setCardInfoObj().getPlayerCard().get(0).getResult().name()
                    .equalsIgnoreCase(requestResult)) {
                result.put("tryCount", count);
                result.put("cardInfo", setCardInfoObj());
                return result;
            }
        }
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
    //카드 한 장 버리고 보드 객체에 저장
    private void setTurn() {
        for (int i =0; i < 2; i++) {
            if (i == 0)
                cardList.remove(cardList.get(i));
            else
                board.add(cardList.get(i));cardList.remove(cardList.get(i));
        }
    }
    //마지막 카드는 버리고 보드 객체에 저장
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
