package com.example.AMS.controller;

import com.example.AMS.dto.UserAndRoleDTO;
import com.example.AMS.entity.UserRoles;
import com.example.AMS.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;
    @GetMapping("/getAllRoles")
    private List<UserRoles> getAllRoles() {
        userRoleService.getAllRoles().forEach(System.out::println);
        return userRoleService.getAllRoles();
    }
}
