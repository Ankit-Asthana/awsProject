package com.example.AMS.service;

import com.example.AMS.dto.ComplaintDTO;
import com.example.AMS.dto.RequestDTO;
import com.example.AMS.entity.Tasks;
import com.example.AMS.entity.Users;
import com.example.AMS.repository.TaskDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskDao taskDao;

    public List<Tasks> getAllTasks() {
        return taskDao.findAll();
    }
    public Tasks addNewComplaint(Tasks tasks) {
        tasks.setTaskType(Tasks.taskType.Complaint);
        tasks.setCreatedDate(new Date());
        return taskDao.save(tasks);
    }

    public Tasks makeNewRequest(Tasks tasks) {
        tasks.setTaskType(Tasks.taskType.Request);
        tasks.setCreatedDate(new Date());
        return taskDao.save(tasks);
    }

    public List<Tasks> getAllRequest() {
        return taskDao.findAllRequests(Tasks.taskType.Request);
    }

    public List<Tasks> getAllComplaint() {
        return taskDao.findAllComplaints(Tasks.taskType.Complaint);
    }

    public List<Tasks> getAllDetailedRequest() {
        return taskDao.findAllComplaints(Tasks.taskType.Complaint);
    }

    public List<RequestDTO> checkAllRequest() {
        return getAllRequest()
                .stream()
                .map(this::convertTaskEntityToRequestDTO)
                .collect(Collectors.toList());
    }

    public RequestDTO convertTaskEntityToRequestDTO(Tasks task) {
        RequestDTO requestDTO= new RequestDTO();
        requestDTO.setTaskId(task.getTaskId());
        requestDTO.setUserName(task.getUsers().getUsername());
        requestDTO.setEmail(task.getUsers().getEmail());
        requestDTO.setFullName(task.getUsers().getFullName());
        requestDTO.setUserId(task.getUsers().getUserId());
        requestDTO.setTaskDescription(task.getDescription());
        requestDTO.setRoleName(task.getUsers().getUserRoles().getRoleName());
        requestDTO.setCreatedDate(task.getCreatedDate());
        requestDTO.setStatus(task.getStatus());
        return requestDTO;
    }

    public List<ComplaintDTO> checkAllComplaint() {
        return getAllComplaint()
                .stream()
                .map(this::convertTaskEntityToComplaintDTO)
                .collect(Collectors.toList());
    }

    private ComplaintDTO convertTaskEntityToComplaintDTO(Tasks task) {
        ComplaintDTO complaintDTO= new ComplaintDTO();
        complaintDTO.setTaskId(task.getTaskId());
        complaintDTO.setUserName(task.getUsers().getUsername());
        complaintDTO.setEmail(task.getUsers().getEmail());
        complaintDTO.setFullName(task.getUsers().getFullName());
        complaintDTO.setUserId(task.getUsers().getUserId());
        complaintDTO.setRoleName(task.getUsers().getUserRoles().getRoleName());
        complaintDTO.setAssetId(task.getAssets().getAssetId());
        complaintDTO.setAssetTypeName(task.getAssets().getAssetTypes().getAssetTypeName());
        complaintDTO.setAssetSerialNo(task.getAssets().getSerialNo());
        complaintDTO.setCategoryType(task.getAssets().getAssetTypes().getCategoryType());
        complaintDTO.setManufacturer(task.getAssets().getManufacturer());
        complaintDTO.setModelName(task.getAssets().getModelName());
        complaintDTO.setComplaintImage(task.getImage());
        complaintDTO.setTaskDescription(task.getDescription());
        complaintDTO.setCreatedDate(task.getCreatedDate());
        complaintDTO.setStatus(task.getStatus());
        return complaintDTO;
    }

    public Tasks findTaskById(Long taskId) {
        return taskDao.findById(taskId).orElse(null);
    }

    public Tasks updateStatus(Tasks task) {
        return taskDao.save(task);
    }

    public boolean taskExists(Long taskId) {
        return taskDao.existsById(taskId);
    }
}
