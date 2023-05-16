package com.example.AMS.controller;

import com.example.AMS.entity.AssetTypes;
import com.example.AMS.service.AssetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AssetTypeController {
    @Autowired
    private AssetTypeService assetTypeService;
    @GetMapping("getAllAssetTypes")
    private List<AssetTypes> getAllAssetTypes(){
        assetTypeService.getAllAssetTypes().forEach(System.out::println);
        return assetTypeService.getAllAssetTypes();
    }

    @PostMapping("registerNewAssetType")
    public ResponseEntity<AssetTypes> registerNewAssetType(@RequestBody AssetTypes assetTypes) {
        System.out.println("Registered Successfully");
        return new ResponseEntity<>(assetTypeService.registerNewAssetType(assetTypes), HttpStatus.CREATED) ;
//        return ResponseEntity.status(HttpStatus.CREATED).body(assetTypeService.registerNewAssetType(assetTypes));
    }
}
