package com.example.AMS.dto;

import lombok.Data;

@Data
public class UserAndRoleDTO {
    private Long userId;
    private String userName;
    private String fullName;
    private String userRole;
    private Boolean status;
}
