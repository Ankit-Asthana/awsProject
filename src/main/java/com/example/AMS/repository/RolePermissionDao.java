package com.example.AMS.repository;

import com.example.AMS.entity.RolePermissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionDao extends JpaRepository<RolePermissions,Long> {
}
