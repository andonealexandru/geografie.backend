package com.geografie.ora_de_geografie.ui.model.request;

import java.util.List;

public class QuestionCreateRequestModel {

    private String classroom;
    private String question;
    private Boolean gridAnswer;
    private List<String> answers;
    private Integer correctAnswer;
    private Boolean photo;
    private String photoUrl;

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Boolean getGridAnswer() {
        return gridAnswer;
    }

    public void setGridAnswer(Boolean gridAnswer) {
        this.gridAnswer = gridAnswer;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public Integer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Integer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Boolean getPhoto() {
        return photo;
    }

    public void setPhoto(Boolean photo) {
        this.photo = photo;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
