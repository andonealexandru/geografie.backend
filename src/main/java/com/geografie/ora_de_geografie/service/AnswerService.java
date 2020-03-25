package com.geografie.ora_de_geografie.service;

import com.geografie.ora_de_geografie.shared.dto.AnswerDto;
import com.geografie.ora_de_geografie.shared.dto.ClassDto;

import java.util.List;

public interface AnswerService {
    List<AnswerDto> getAnswersByClass(ClassDto classDto);
}
