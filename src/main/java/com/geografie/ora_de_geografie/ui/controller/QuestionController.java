package com.geografie.ora_de_geografie.ui.controller;

import com.geografie.ora_de_geografie.service.QuestionService;
import com.geografie.ora_de_geografie.shared.dto.QuestionDto;
import com.geografie.ora_de_geografie.ui.model.request.QuestionCreateRequestModel;
import com.geografie.ora_de_geografie.ui.model.response.QuestionRest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public QuestionRest createQuestion(@RequestBody QuestionCreateRequestModel questionCreateRequestModel) {

        ModelMapper modelMapper = new ModelMapper();

        QuestionDto questionDto = modelMapper.map(questionCreateRequestModel, QuestionDto.class);
        QuestionDto createdQuestion = questionService.createQuestion(questionDto);

        QuestionRest questionRest = modelMapper.map(createdQuestion, QuestionRest.class);

        return questionRest;
    }
}
