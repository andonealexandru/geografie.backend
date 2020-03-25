package com.geografie.ora_de_geografie.io.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "answers")
public class AnswerEntity implements Serializable {

    private static final long serialVersionUID = 1310142025978983008L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity questionId;

    @Column(nullable = false)
    private String answer;

    @Column(nullable = false)
    private Boolean correct;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionEntity getQuestionId() {
        return questionId;
    }

    public void setQuestionId(QuestionEntity questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }
}
