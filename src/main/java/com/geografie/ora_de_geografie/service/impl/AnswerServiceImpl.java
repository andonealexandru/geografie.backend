package com.geografie.ora_de_geografie.service.impl;

import com.geografie.ora_de_geografie.io.entity.AnswerEntity;
import com.geografie.ora_de_geografie.io.entity.QuestionEntity;
import com.geografie.ora_de_geografie.io.repository.AnswerRepository;
import com.geografie.ora_de_geografie.io.repository.ClassRepository;
import com.geografie.ora_de_geografie.service.AnswerService;
import com.geografie.ora_de_geografie.shared.dto.AnswerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    ClassRepository classRepository;

    @Override
    public List<AnswerDto> getAnswersByQuestion(QuestionEntity questionEntity) {
        List<AnswerEntity> answerEntities = answerRepository.findByQuestionId(questionEntity);

        ModelMapper modelMapper = new ModelMapper();
        List<AnswerDto> answerDtos = new ArrayList<>();

        for (int i = 0; i < answerEntities.size(); ++i) {
            answerDtos.add(modelMapper.map(answerEntities.get(i), AnswerDto.class));
        }

        return answerDtos;
    }
}
