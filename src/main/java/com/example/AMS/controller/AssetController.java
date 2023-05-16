package com.example.AMS.controller;

import com.example.AMS.entity.Assets;
import com.example.AMS.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class AssetController {
    @Autowired
    private AssetService assetService;
    @GetMapping("getAllAsset")
    private List<Assets> getAllAssets()
    {
        return assetService.getAllAssets();
    }

    @GetMapping("/getAssetById/{assetId}")
    private Assets getAssetById(@PathVariable Long assetId)
    {
        Assets asset = assetService.getAssetById(assetId);
        if(asset == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Assignment with ID= "+assetId+"  Not Found");
        }
        return asset;
    }
    @PostMapping("addNewAsset")
    public ResponseEntity<Assets> addNewAsset(@RequestBody Assets assets) {
        System.out.println("Registered Successfully");
        return new ResponseEntity<>(assetService.addNewAsset(assets),HttpStatus.CREATED) ;
    }
//    @PostMapping("addNewAsset")
//    public Assets addNewAsset(@RequestPart("asset") Assets assets, @RequestParam(value = "file", required = false) MultipartFile file) {
//        if (file != null) {
//            String fileName = assetService.uploadFile(file);
//            assets.setPurchaseInvoice(fileName.getBytes());
//        }
//        System.out.println("Registered Successfully");
//        return assetService.addNewAsset(assets);
//    }

//    @PostMapping
//    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
//        String uploadImage = service.uploadImage(file);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(uploadImage);
//    }
//
//    @GetMapping("/{fileName}")
//    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
//        byte[] imageData=service.downloadImage(fileName);
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf("image/png"))
//                .body(imageData);
//    }
}
