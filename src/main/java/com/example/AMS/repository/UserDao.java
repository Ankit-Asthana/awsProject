package com.example.AMS.repository;

import com.example.AMS.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<Users,Long> {
    @Query("select u FROM Users u where u.status=true")
//    @Query(value = "select * from users where status=true",nativeQuery = true) //should also work
    List<Users> findAllActiveUsers();

    <Optional> Users findByEmail(String email);

//    @Query("select ur.roleName from User u,UserRoles ur where u.userId = :id")
//    @Query("select ur.roleName from User u join u.userRoles ur where u.userId = :id")
//    String findUserRoleNameByUserId(@Param("id") Long userId);
}
