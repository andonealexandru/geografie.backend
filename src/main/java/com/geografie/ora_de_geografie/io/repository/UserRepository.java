package com.geografie.ora_de_geografie.io.repository;

import com.geografie.ora_de_geografie.io.entity.ClassEntity;
import com.geografie.ora_de_geografie.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    UserEntity findByUserId(String userId);
    UserEntity findByEmailConfirmationToken(String emailConfirmationToken);
    List<UserEntity> findByClassId(ClassEntity classEntity);
}
