package com.example.AMS.controller;

import com.example.AMS.entity.AssetTransferHistory;
import com.example.AMS.service.AssetTransferHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class AssetTransferHistoryController {
    @Autowired
    private AssetTransferHistoryService assetTransferHistoryService;

    @GetMapping("/getAllTransfers")
    private List<AssetTransferHistory> getAllTransfers()
    {
        return assetTransferHistoryService.getAllAssignment();
    }
    @GetMapping("/getTransferHistoryById/{historyId}")
    private AssetTransferHistory getTransferById(@PathVariable Long historyId)
    {
        AssetTransferHistory transferHistory = assetTransferHistoryService.getAssignmentById(historyId);
        if(transferHistory == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transfer History with ID= "+historyId+"  Not Found");
        }
        return transferHistory;
    }
}
