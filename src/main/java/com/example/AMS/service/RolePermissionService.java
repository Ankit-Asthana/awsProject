package com.example.AMS.service;

import com.example.AMS.entity.RolePermissions;
import com.example.AMS.repository.RolePermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionService {
    @Autowired
    private RolePermissionDao permissionDao;
    public List<RolePermissions> getAllRolePermissions() {
        return permissionDao.findAll();
    }
}
