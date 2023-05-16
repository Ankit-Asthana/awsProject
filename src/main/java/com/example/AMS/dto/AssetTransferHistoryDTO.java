package com.example.AMS.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AssetTransferHistoryDTO {
    private Long assignId;
    private Long assetId;
    private String assetTypeName;
    private String modelName;
    private String manufacturer;
    private Long serialNo;
    private Long userId;
    private String userName;
    private String fullName;
    private String userRoleName;
    private Date issuedDate;
    private Date issuedTime;
}
