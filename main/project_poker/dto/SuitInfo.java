package project_poker.dto;

public enum SuitInfo {
    DIAMOND("diamond", "♦"),
    SPADE("spade", "♠"),
    HEART("heart", "❤"),
    CLUB("club", "♣");
    private final String name;
    private final String shade;
    SuitInfo(String name, String shade) {
        this.name = name;
        this.shade = shade;
    }
    public String getShade() {
        return shade;
    }
}
