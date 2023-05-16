package com.example.AMS.service;

import com.example.AMS.dto.ComplaintDTO;
import com.example.AMS.dto.UserAndRoleDTO;
import com.example.AMS.entity.UserRoles;
import com.example.AMS.repository.UserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleService {
    @Autowired
    private UserRoleDao userRoleDao;

    public List<UserRoles> getAllRoles() {
        return userRoleDao.findAll();
    }

}
