package com.geografie.ora_de_geografie.service.impl;

import com.geografie.ora_de_geografie.io.entity.AnswerEntity;
import com.geografie.ora_de_geografie.io.entity.QuestionEntity;
import com.geografie.ora_de_geografie.io.repository.AnswerRepository;
import com.geografie.ora_de_geografie.io.repository.ClassRepository;
import com.geografie.ora_de_geografie.io.repository.QuestionRepository;
import com.geografie.ora_de_geografie.service.AnswerService;
import com.geografie.ora_de_geografie.service.QuestionService;
import com.geografie.ora_de_geografie.shared.Utils;
import com.geografie.ora_de_geografie.shared.dto.QuestionDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ClassRepository classRepository;

    @Autowired
    AnswerService answerService;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    Utils utils;

    @Override
    public QuestionDto createQuestion(QuestionDto questionDto) {
        ModelMapper modelMapper = new ModelMapper();

        QuestionEntity questionEntity = modelMapper.map(questionDto, QuestionEntity.class);

        questionEntity.setQuestionId(utils.generateDummyId(30));
        questionEntity.setClassId(classRepository.findByClassId(questionDto.getClassroom()));
        List<AnswerEntity> answerEntities = new ArrayList<>();
        questionEntity.setAnswers(answerEntities);

        QuestionEntity storedQuestionEntity = questionRepository.save(questionEntity);

        for (int i = 0; i < questionDto.getAnswers().size(); ++i) {
            String answer = questionDto.getAnswers().get(i);

            AnswerEntity answerEntity = new AnswerEntity();
            answerEntity.setAnswer(answer);
            answerEntity.setQuestionId(storedQuestionEntity);
            //AnswerEntity savedAnswerEntity = answerRepository.save(answerEntity);

            if (i == questionDto.getCorrectAnswer()) answerEntity.setCorrect(true);
            else answerEntity.setCorrect(false);

            answerEntities.add(answerEntity);
        }

        QuestionDto response = modelMapper.map(storedQuestionEntity, QuestionDto.class);
        List<String> responseAnswers = new ArrayList<>();

        for (int i = 0; i < answerEntities.size(); ++i) {
            responseAnswers.add(answerEntities.get(i).getAnswer());
            if (i == questionDto.getCorrectAnswer()) response.setCorrectAnswer(i);
            answerRepository.save(answerEntities.get(i));
        }

        response.setAnswers(responseAnswers);

        return response;
    }
}
