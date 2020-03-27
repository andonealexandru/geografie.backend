package com.geografie.ora_de_geografie.service;

import com.geografie.ora_de_geografie.io.entity.QuestionEntity;
import com.geografie.ora_de_geografie.shared.dto.AnswerDto;

import java.util.List;

public interface AnswerService {
    List<AnswerDto> getAnswersByQuestion(QuestionEntity questionEntity);
}
