package com.training.aem.core.models;

public class FaqEntity {

    private String question;
    private String answer;

    public FaqEntity(){

    }


    public FaqEntity(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
