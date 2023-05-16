package com.example.AMS.service;

import com.example.AMS.entity.Assets;
import com.example.AMS.repository.AssetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class AssetService {
    @Autowired
    private AssetDao assetDao;
    public List<Assets> getAllAssets() {
//        System.out.println(assetDao.findAll().stream().findAny().get().getAssetTypes().getCategoryType());
        return assetDao.findAll();
    }
    public Assets addNewAsset(Assets assets) {
        assets.setCreatedDate(new Date());
        return assetDao.save(assets);
    }

    public boolean assetExists(long assetId) {
        return assetDao.existsById(assetId);
    }

    public Assets getAssetById(Long assetId) {
        return assetDao.findById(assetId).orElse(null);
    }
    public void updateIsAssigned(Long assetId,Boolean update){
        Assets asset =assetDao.findById(assetId).orElse(null);
        asset.setIsAssigned(update);
        assetDao.save(asset);
    }

//    public String uploadFile(MultipartFile file) {
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        try {
//            Path path = Paths.get("/path/to/save/uploaded/files" + fileName);
//            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return fileName;
//    }

//    public String uploadImage(MultipartFile file) throws IOException {
//
//        ImageData imageData = repository.save(ImageData.builder()
//                .name(file.getOriginalFilename())
//                .type(file.getContentType())
//                .imageData(ImageUtils.compressImage(file.getBytes())).build());
//        if (imageData != null) {
//            return "file uploaded successfully : " + file.getOriginalFilename();
//        }
//        return null;
//    }
//
//        public byte[] downloadImage(String fileName){
//            Optional<ImageData> dbImageData = repository.findByName(fileName);
//            byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
//            return images;
//        }
}
