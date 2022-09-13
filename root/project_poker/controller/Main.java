package controller;

import service.SettingCardService;

public class Main {
    private final static SettingCardService cardService = new SettingCardService();
    public static void main(String[] args) {
        cardService.startGame();
    }
}
