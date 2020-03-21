package com.geografie.ora_de_geografie.shared.dto;

import java.io.Serializable;
import java.util.List;

public class QuestionDto implements Serializable {

    private static final long serialVersionUID = -4285463440892975827L;

    private Long id;
    private String questionId;
    private String classId;
    private String classroom;
    private String question;
    private Boolean gridAnswer;
    private List<String> answers;
    private Integer correctAnswer;
    private Boolean photo;
    private String photoUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

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
