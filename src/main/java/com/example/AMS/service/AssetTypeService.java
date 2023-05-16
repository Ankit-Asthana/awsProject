package com.example.AMS.service;

import com.example.AMS.entity.AssetTypes;
import com.example.AMS.repository.AssetTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetTypeService {
    @Autowired
    private AssetTypeDao assetTypeDao;
    public List<AssetTypes> getAllAssetTypes() {
        return assetTypeDao.findAll();
    }

    public AssetTypes registerNewAssetType(AssetTypes assetTypes) {
        return assetTypeDao.save(assetTypes);
    }
}
