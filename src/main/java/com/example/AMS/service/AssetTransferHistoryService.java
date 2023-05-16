package com.example.AMS.service;

import com.example.AMS.entity.AssetTransferHistory;
import com.example.AMS.repository.AssetTransferHistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetTransferHistoryService {
    @Autowired
    private AssetTransferHistoryDao assetTransferHistoryDao;
    @Autowired
    private AssetService assetService;
    @Autowired
    private UserService userService;

    public  AssetTransferHistory getAssignmentById(Long historyId) {
        return assetTransferHistoryDao.findById(historyId).orElse(null);
    }

    public void addHistory(Long assetId, Long userId) {
        AssetTransferHistory transferHistory = new AssetTransferHistory();
        transferHistory.setAssets(assetService.getAssetById(assetId));
        transferHistory.setUsers(userService.getUserById(userId));
        assetTransferHistoryDao.save(transferHistory);
    }

    public List<AssetTransferHistory> getAllAssignment() {
        return assetTransferHistoryDao.findAll();
    }
}
