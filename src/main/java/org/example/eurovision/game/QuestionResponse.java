package org.example.eurovision.game;

public class QuestionResponse {
    private int questionId;
    private String questionText;
    private QuestionType type;
    private String[] options;

    public QuestionResponse(int questionId, String questionText, QuestionType type, String[] options) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.type = type;
        this.options = options;
    }

    // Getters and Setters

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}
