package com.geografie.ora_de_geografie.io.repository;

import com.geografie.ora_de_geografie.io.entity.RoleEntity;
import com.geografie.ora_de_geografie.io.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(RoleName name);
}
