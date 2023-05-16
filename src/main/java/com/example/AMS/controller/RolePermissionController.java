package com.example.AMS.controller;

import com.example.AMS.entity.RolePermissions;
import com.example.AMS.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RolePermissionController {
    @Autowired
    private RolePermissionService rolePermissionService;
    @GetMapping("/getAllRolePermissions")
    private List<RolePermissions> getAllRolePermissions()
    {
        rolePermissionService.getAllRolePermissions().forEach(System.out::println);
        return rolePermissionService.getAllRolePermissions();
    }
}
