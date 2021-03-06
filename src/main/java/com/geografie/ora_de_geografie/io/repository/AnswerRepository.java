package com.geografie.ora_de_geografie.io.repository;

import com.geografie.ora_de_geografie.io.entity.AnswerEntity;
import com.geografie.ora_de_geografie.io.entity.QuestionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<AnswerEntity, Long> {
    List<AnswerEntity> findByQuestionId(QuestionEntity questionEntity);
}
