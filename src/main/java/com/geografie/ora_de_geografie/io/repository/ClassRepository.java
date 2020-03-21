package com.geografie.ora_de_geografie.io.repository;

import com.geografie.ora_de_geografie.io.entity.ClassEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends CrudRepository<ClassEntity, Long> {
    ClassEntity findByName(String name);
    ClassEntity findByClassId(String classId);
}
