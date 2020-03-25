package com.geografie.ora_de_geografie.service;

import com.geografie.ora_de_geografie.shared.dto.ClassDto;
import com.geografie.ora_de_geografie.shared.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClassService {
    ClassDto createClass(ClassDto classDto);
    List<UserDto> getUsersInClass(String classroom);
}
