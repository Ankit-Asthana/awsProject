package com.example.AMS.dto;

import com.example.AMS.entity.Tasks;
import lombok.Data;

import java.util.Date;

@Data
public class RequestDTO {
    private Long taskId;
    private Long userId;
    private String userName;
    private String roleName;
    private String fullName;
    private String email;
    private String taskDescription;
    private Date createdDate;
    private Tasks.status status;
}
