package com.geografie.ora_de_geografie.ui.controller;

import com.geografie.ora_de_geografie.service.ClassService;
import com.geografie.ora_de_geografie.shared.dto.ClassDto;
import com.geografie.ora_de_geografie.shared.dto.UserDto;
import com.geografie.ora_de_geografie.ui.model.request.ClassCreateRequestModel;
import com.geografie.ora_de_geografie.ui.model.response.ClassRest;
import com.geografie.ora_de_geografie.ui.model.response.UserRest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("class")
public class ClassController {

    @Autowired
    ClassService classService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ClassRest createClass(@RequestBody ClassCreateRequestModel classCreateRequestModel) throws Exception {

        ModelMapper modelMapper = new ModelMapper();

        ClassDto classDto = modelMapper.map(classCreateRequestModel, ClassDto.class);

        ClassDto createdClass = classService.createClass(classDto);
        ClassRest classRest = modelMapper.map(createdClass, ClassRest.class);

        return classRest;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<UserRest> getUsers(@RequestParam String classroom) {

        ModelMapper modelMapper = new ModelMapper();

        List<UserDto> usersInClass = classService.getUsersInClass(classroom);
        List<UserRest> response = new ArrayList<>();

        for (int i = 0; i < usersInClass.size(); ++i) {
            response.add(modelMapper.map(usersInClass.get(i), UserRest.class));
        }

        return response;
    }
}
