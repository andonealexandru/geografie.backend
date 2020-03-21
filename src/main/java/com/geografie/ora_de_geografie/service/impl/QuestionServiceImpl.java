package com.geografie.ora_de_geografie.service.impl;

import com.geografie.ora_de_geografie.io.entity.QuestionEntity;
import com.geografie.ora_de_geografie.io.repository.ClassRepository;
import com.geografie.ora_de_geografie.io.repository.QuestionRepository;
import com.geografie.ora_de_geografie.service.QuestionService;
import com.geografie.ora_de_geografie.shared.Utils;
import com.geografie.ora_de_geografie.shared.dto.QuestionDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ClassRepository classRepository;

    @Autowired
    Utils utils;

    @Override
    public QuestionDto createQuestion(QuestionDto questionDto) {
        ModelMapper modelMapper = new ModelMapper();

        QuestionEntity questionEntity = modelMapper.map(questionDto, QuestionEntity.class);

        questionEntity.setQuestionId(utils.generateDummyId(30));
        questionEntity.setClassId(classRepository.findByClassId(questionDto.getClassroom()));

        QuestionEntity storedQuestionEntity = questionRepository.save(questionEntity);

        return modelMapper.map(storedQuestionEntity, QuestionDto.class);
    }
}
