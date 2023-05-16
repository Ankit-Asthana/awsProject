package com.example.AMS.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;
@Data
public class UserDTO {
    private Long userId;
    private String userName;
    private String fullName;
    private String roleName;
    private String roleDescription;
    private String email;
    private Boolean status;
    private Date createdDate;
}
