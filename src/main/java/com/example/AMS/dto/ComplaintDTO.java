package com.example.AMS.dto;

import com.example.AMS.entity.AssetTypes;
import com.example.AMS.entity.Tasks;
import lombok.Data;

import java.util.Date;
@Data
public class ComplaintDTO {
    private Long taskId;
    private Long userId;
    private String userName;
    private String roleName;
    private String fullName;
    private String email;
    private Long assetId;
    private String assetTypeName;
    private AssetTypes.categoryType categoryType;
    private Long assetSerialNo;
    private String modelName;
    private String manufacturer;
    private String taskDescription;
    private Date createdDate;
    private byte[] complaintImage;
    private Tasks.status status;
}
