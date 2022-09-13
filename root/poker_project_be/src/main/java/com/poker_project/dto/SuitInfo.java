package com.poker_project.dto;

public enum SuitInfo {
    DIAMOND("♦"),
    SPADE("♠"),
    HEART("❤"),
    CLUB("♣");
    private final String shade;
    SuitInfo(String shade) {
        this.shade = shade;
    }
    public String getShade() {
        return shade;
    }
}
