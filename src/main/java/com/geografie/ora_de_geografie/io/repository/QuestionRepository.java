package com.geografie.ora_de_geografie.io.repository;

import com.geografie.ora_de_geografie.io.entity.ClassEntity;
import com.geografie.ora_de_geografie.io.entity.QuestionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<QuestionEntity, Long> {
    List<QuestionEntity> findByClassId(ClassEntity classEntity);
    QuestionEntity findByQuestionId(String questionId);
}
