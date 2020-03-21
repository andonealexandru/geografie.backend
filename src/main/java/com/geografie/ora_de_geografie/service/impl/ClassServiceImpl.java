package com.geografie.ora_de_geografie.service.impl;

import com.geografie.ora_de_geografie.io.entity.ClassEntity;
import com.geografie.ora_de_geografie.io.entity.UserEntity;
import com.geografie.ora_de_geografie.io.repository.ClassRepository;
import com.geografie.ora_de_geografie.io.repository.UserRepository;
import com.geografie.ora_de_geografie.service.ClassService;
import com.geografie.ora_de_geografie.shared.Utils;
import com.geografie.ora_de_geografie.shared.dto.ClassDto;
import com.geografie.ora_de_geografie.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    ClassRepository classRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Override
    public ClassDto createClass(ClassDto classDto) {

        if (classRepository.findByName(classDto.getName()) != null)
            throw new RuntimeException("Class already exists!");

        ModelMapper modelMapper = new ModelMapper();

        ClassEntity classEntity = modelMapper.map(classDto, ClassEntity.class);
        classEntity.setClassId(utils.generateDummyId(30));

        ClassEntity storedClassEntity = classRepository.save(classEntity);

        return modelMapper.map(storedClassEntity, ClassDto.class);
    }

    @Override
    public List<UserDto> getUsersInClass(String classroom) {

        ClassEntity classEntity = classRepository.findByClassId(classroom);

        if (classEntity == null)
            throw new RuntimeException("Class not found");

        ModelMapper modelMapper = new ModelMapper();

        List<UserEntity> userEntities = userRepository.findByClassId(classEntity);
        List<UserDto> response = new ArrayList<>();

        for (int i = 0; i < userEntities.size(); ++i) {
            UserDto userDto = modelMapper.map(userEntities.get(i), UserDto.class);
            userDto.setClassroom(classroom);
            response.add(userDto);
        }

        return response;
    }
}
