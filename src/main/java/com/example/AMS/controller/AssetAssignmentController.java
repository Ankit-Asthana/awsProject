package com.example.AMS.controller;

import com.example.AMS.entity.AssetAssignment;
import com.example.AMS.service.AssetAssignmentService;
import com.example.AMS.service.AssetService;
import com.example.AMS.service.AssetTransferHistoryService;
import com.example.AMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AssetAssignmentController {
    @Autowired
    private AssetAssignmentService assetAssignmentService;
    @Autowired
    private AssetService assetService;
    @Autowired
    private UserService userService;

    @Autowired
    private AssetTransferHistoryService historyService;
    @PostMapping("registerNewAssignment")
    public ResponseEntity<AssetAssignment> newAssignment(@RequestBody AssetAssignment assetAssignment) {
        Long userId = assetAssignment.getUsers().getUserId();
        Long assetId = assetAssignment.getAssets().getAssetId();

        checkUserAndAssetExist(assetId,userId);

        if(assetService.getAssetById(assetId).getIsAssigned())
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Asset is Already Assigned ");
        assetService.updateIsAssigned(assetId,true);
        historyService.addHistory(assetId,userId);
        return new ResponseEntity<>(assetAssignmentService.newAssignment(assetAssignment),HttpStatus.CREATED);//saving assignment
    }
    
    public void checkUserAndAssetExist(Long assetId,Long userId){ //checking if user and assets exists or not
        if (!userService.userExists(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with Id=" + userId + "  Not Found");
        }
        if (!assetService.assetExists(assetId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "asset with Id=" + assetId + "  Not Found");
        }
    }
    @GetMapping("getAllAssetAssignmentSummary")
    private List<String> getAllAssignmentSummary(){
        List<String> data = new ArrayList<>();
        assetAssignmentService.getAllAssignment().forEach(assetAssignment -> data.add(
                "UserName :"+assetAssignment.getUsers().getUsername()+
                " | Asset :"+assetAssignment.getAssets().getModelName()+
                " | SerialNo :"+assetAssignment.getAssets().getSerialNo()+
                " | AssetType :"+assetAssignment.getAssets().getAssetTypes().getAssetTypeName()));
        return data;
    }
    @GetMapping("getAssetAssignmentSummary/{assignId}")
    private String getAssetAssignmentSummary(@PathVariable Long assignId){
        AssetAssignment assetAssignment = assetAssignmentService.getAssignmentById(assignId);
        if (assetAssignment!=null){
            return "UserName :"+assetAssignment.getUsers().getUsername()+
                    " | Asset :"+assetAssignment.getAssets().getModelName()+
                    " | SerialNo :"+assetAssignment.getAssets().getSerialNo()+
                    " | AssetType :"+assetAssignment.getAssets().getAssetTypes().getAssetTypeName();
        }
       else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Assignment with ID= "+assignId+"  Not Found");
        }
    }

    @GetMapping("/getAllAssignments")
    private List<AssetAssignment> getAllAssignment()
    {
        return assetAssignmentService.getAllAssignment();
    }
    @GetMapping("/getAssignmentById/{assignId}")
    private AssetAssignment getAssignmentById(@PathVariable Long assignId)
    {
        AssetAssignment assetAssignment = assetAssignmentService.getAssignmentById(assignId);
        if(assetAssignment == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Assignment with ID= "+assignId+"  Not Found");
        }
        return assetAssignment;
    }
    @DeleteMapping("/deleteAssignment/{id}")
    public String deleteAssignment(@PathVariable Long id) {
        if(assetAssignmentService.getAssignmentById(id)==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Assignment Id entered is incorrect");
        else{
            try {
                assetAssignmentService.deleteAssignment(id);
                return "Assignment deleted successfully";
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Assignment Cannot be Deleted",e);
            }
        }
    }
}
