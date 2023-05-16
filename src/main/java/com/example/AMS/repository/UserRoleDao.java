package com.example.AMS.repository;

import com.example.AMS.entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDao extends JpaRepository<UserRoles,Long> {
    UserRoles findByRoleName(String roleName);
}
