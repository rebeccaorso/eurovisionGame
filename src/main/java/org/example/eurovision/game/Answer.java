package org.example.eurovision.game;

public class Answer {
    private int questionId;
    private String selectedOption;
    private QuestionType type;

    public Answer(int questionId, String selectedOption, QuestionType type) {
        this.questionId = questionId;
        this.selectedOption = selectedOption;
        this.type = type;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }
}