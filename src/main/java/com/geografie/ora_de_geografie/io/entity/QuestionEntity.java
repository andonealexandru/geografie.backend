package com.geografie.ora_de_geografie.io.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "questions")
public class QuestionEntity implements Serializable {

    private static final long serialVersionUID = 7374727710290127156L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String questionId;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity classId;

    @Column(nullable = false, length = 100)
    private String question;

    @Column(nullable = false)
    private Boolean gridAnswer;

    @OneToMany(mappedBy = "questionId", cascade = CascadeType.ALL)
    private List<AnswerEntity> answers;

    @Column(nullable = false)
    private Boolean photo;

    @Column
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

    public ClassEntity getClassId() {
        return classId;
    }

    public void setClassId(ClassEntity classId) {
        this.classId = classId;
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

    public List<AnswerEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerEntity> answers) {
        this.answers = answers;
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
