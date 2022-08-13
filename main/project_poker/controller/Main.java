package project_poker.controller;

import project_poker.service.SettingCardService;

public class Main {
    private final static SettingCardService cardService = new SettingCardService();
    public static void main(String[] args) {
        cardService.startGame();
    }
}
