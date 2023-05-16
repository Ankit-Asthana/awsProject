package com.example.AMS.controller;

import com.example.AMS.dto.ComplaintDTO;
import com.example.AMS.dto.RequestDTO;
import com.example.AMS.entity.Tasks;
import com.example.AMS.entity.Users;
import com.example.AMS.exception.ApiException;
import com.example.AMS.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("makeNewRequest")
    public ResponseEntity<Tasks> makeNewRequest(@RequestBody Tasks tasks){
        try{
            System.out.println("Requested Successfully");
            return new ResponseEntity<>(taskService.makeNewRequest(tasks),HttpStatus.CREATED) ;
        }
        catch(Exception ex){
            throw new ApiException(HttpStatus.BAD_REQUEST,"Incorrect Request type");
        }
    }

    @PostMapping("addNewComplaint")
    public ResponseEntity<Tasks> addNewComplaint(@RequestBody Tasks tasks){
        try{
            System.out.println("Complaint Added");
            return new ResponseEntity<>(taskService.addNewComplaint(tasks),HttpStatus.CREATED);
        }
        catch(Exception ex){
            throw new ApiException(HttpStatus.BAD_REQUEST,"Incorrect Complaint");
        }
    }
    @GetMapping("/getAllRequests")
    private List<String> getAllRequest()
    {
        List<String> data = new ArrayList<>();
        taskService.getAllRequest().forEach(tasks -> data.add(
                "UserName :"+tasks.getUsers().getUsername()+
                        " | TaskType :"+tasks.getTaskType()+
                        " | Description :"+tasks.getDescription()));
        return data;
    }


    @GetMapping("getDetailedRequests")
    private List<Tasks> getAllDetailedRequests(){
            return taskService.getAllRequest();
    }

    @GetMapping("checkAllRequests")
    private List<RequestDTO> checkAllRequests(){
        return taskService.checkAllRequest();
    }

    @GetMapping("checkAllComplaints")
    private List<ComplaintDTO> checkAllComplaint(){
        return taskService.checkAllComplaint();
    }

    @GetMapping("/getAllComplaint")
    private List<String> getAllComplaint()
    {
        List<String> data = new ArrayList<>();
        taskService.getAllComplaint().forEach(tasks -> data.add(
                "UserName :"+tasks.getUsers().getUsername()+
                        " | TaskType :"+tasks.getTaskType()+
                        " | Description :"+tasks.getDescription()));
        return data;
    }
    @PutMapping("updateTaskStatus/{taskId}/{status}")
    private Tasks updateTaskStatus(@PathVariable Long taskId,@PathVariable Tasks.status status)
    {
        if(!taskService.taskExists(taskId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with Id=" + taskId + "  Not Found");
        Tasks task=taskService.findTaskById(taskId);
        task.setStatus(status);
        return taskService.updateStatus(task);
    }
    @GetMapping("checkStatusOptions")
    private List<Tasks.status> checkStatusOptions(){
        return List.of(
                Tasks.status.Open,
                Tasks.status.Approved,
                Tasks.status.Review,
                Tasks.status.Rejected
        );
    }
    @GetMapping("/getDetailedComplaints")
    private List<Tasks> getAllDetailedComplaint()
    {
        return taskService.getAllComplaint();
    }
}
