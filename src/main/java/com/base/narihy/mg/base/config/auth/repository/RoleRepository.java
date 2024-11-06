package com.base.narihy.mg.base.config.auth.repository;

import com.base.narihy.mg.base.config.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}