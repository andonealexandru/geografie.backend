package com.geografie.ora_de_geografie.ui.controller;

import com.geografie.ora_de_geografie.service.QuestionService;
import com.geografie.ora_de_geografie.service.UserService;
import com.geografie.ora_de_geografie.shared.dto.QuestionDto;
import com.geografie.ora_de_geografie.shared.dto.UserDto;
import com.geografie.ora_de_geografie.ui.model.request.QuestionCreateRequestModel;
import com.geografie.ora_de_geografie.ui.model.response.QuestionRest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    UserService userService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public QuestionRest createQuestion(@RequestBody QuestionCreateRequestModel questionCreateRequestModel) {

        ModelMapper modelMapper = new ModelMapper();

        QuestionDto questionDto = modelMapper.map(questionCreateRequestModel, QuestionDto.class);
        QuestionDto createdQuestion = questionService.createQuestion(questionDto);

        QuestionRest questionRest = modelMapper.map(createdQuestion, QuestionRest.class);

        return questionRest;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public QuestionRest getQuestion(@RequestParam String userId) {

        ModelMapper modelMapper = new ModelMapper();

        UserDto userDto = userService.getUserByUserId(userId);

        QuestionDto questionDto = questionService.getQuestion(userDto.getClassroom());

        QuestionRest questionRest = modelMapper.map(questionDto, QuestionRest.class);

        return questionRest;
    }
}
