package org.example.eurovision.game;

public enum Difficulty {
    FACILE(3),
    MEDIO(4),
    DIFFICILE(5);

    private final int optionCount;

    Difficulty(int optionCount) {
        this.optionCount = optionCount;
    }

    public int getOptionCount() {
        return optionCount;
    }
}