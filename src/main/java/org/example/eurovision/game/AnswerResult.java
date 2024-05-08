package org.example.eurovision.game;

public class AnswerResult {
    private boolean isCorrect;

    public AnswerResult(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}
