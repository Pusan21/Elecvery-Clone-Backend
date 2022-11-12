package com.Pusan21.ElecveryCloneBackend.repository;

import com.Pusan21.ElecveryCloneBackend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
