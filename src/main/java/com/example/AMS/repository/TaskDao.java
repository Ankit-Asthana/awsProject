package com.example.AMS.repository;

import com.example.AMS.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDao extends JpaRepository<Tasks,Long> {

    @Query("select t FROM Tasks t where t.taskType= :requestType")
    List<Tasks> findAllRequests(@Param("requestType") Tasks.taskType requestType);

    @Query("select t FROM Tasks t where t.taskType= :complaintType")
    List<Tasks> findAllComplaints(@Param("complaintType") Tasks.taskType complaintType);
}
