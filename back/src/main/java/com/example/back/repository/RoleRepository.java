package com.example.back.repository;

import com.example.back.model.Role;
import com.example.back.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(RoleName name);
}
