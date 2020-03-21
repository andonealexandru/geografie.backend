package com.geografie.ora_de_geografie.io.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "classes")
public class ClassEntity implements Serializable {

    private static final long serialVersionUID = -6027584841189316131L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String classId;

    @Column(nullable = false, length = 20)
    private String name;

    @OneToMany(mappedBy = "classId", cascade = CascadeType.ALL)
    private List<UserEntity> users;

    @OneToMany(mappedBy = "classId", cascade = CascadeType.ALL)
    private List<QuestionEntity> questions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
