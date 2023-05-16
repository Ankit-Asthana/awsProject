package com.example.AMS.controller;

import com.example.AMS.dto.UserAndRoleDTO;
import com.example.AMS.dto.UserDTO;
import com.example.AMS.entity.Users;
import com.example.AMS.exception.ApiException;
import com.example.AMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

//    @PostMapping("registerNewUser")
//    public Users registerNewUser(@RequestBody Users users) {
//        try{
//            System.out.println("Registered Successfully");
//            return userService.registerNewUser(users) ;
//        }
//        catch(Exception ex){
//            throw new ApiException(HttpStatus.BAD_REQUEST,"User Data Entered Incorrectly");
//        }
//    }
    @PostMapping("/registerNewUser")
    public ResponseEntity<Users> registerNewUser(@RequestBody Users users) {
        try{
            System.out.println("Registered Successfully");
            return new ResponseEntity<>(userService.registerNewUser(users),HttpStatus.CREATED);
        }
        catch(Exception ex){
            throw new ApiException(HttpStatus.BAD_REQUEST,"User Data Entered Incorrectly");
        }
    }

//    @GetMapping("/getAllUsers")
//    private List<Users> getAllUsers()
//    {
//        userService.getAllUsers().forEach(System.out::println);
//        return userService.getAllUsers();
//    }
    @GetMapping("/getAllUsers")
    private ResponseEntity<List<Users>> getAllUser()
    {
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }
    @GetMapping("/getUsers")
    private ResponseEntity<List<UserDTO>> getUser()
    {
        if(userService.getUsers().isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No users added");
        return new ResponseEntity<>(userService.getUsers(),HttpStatus.OK);
    }

    @GetMapping("/getUsersById/{userId}")
    private Users getUserById(@PathVariable Long userId)
    {
        Users user = userService.getUserById(userId);
        if(user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with Id=" + userId +"  Not Found");
        }
//        System.out.println(user);
        return user;
    }

    @GetMapping("getAllActiveUsers")
    private List<Users> getAllActiveUsers(){
        if(userService.getAllActiveUsers().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No Active Users Found");
        }
        return userService.getAllActiveUsers();
    }
    @GetMapping("getRoleNameByUserId/{userId}")
    private String getRoleNameByUserId(@PathVariable Long userId){
        Users user= userService.getUserById(userId);
        if(user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with Id=" + userId +"  Not Found");
        }
        System.out.println(user.getUserRoles().getRoleName());
        return user.getUserRoles().getRoleName();

//        String x="The User with the name " + user.getUserName() + " have the Role :" + user.getUserRoles().getRoleName();
//        return x;//to show detailed data

//        return userService.getUserRoleNameByUserId(userId); //for trying the join query method
    }
    @GetMapping("getAllUserAndRole")
    private List<String> getAllUserAndRole(){
        List<String> data = new ArrayList<>();
        userService.getAllUsers().forEach(users -> data.add("UserName :"+users.getUsername()+
                " | UserRole :"+users.getUserRoles().getRoleName()+
                " | Status :"+users.getStatus()));
        return data;
    }
        @PutMapping("/updateUser/{id}")
    public Users updateUser(@RequestBody Users user, @PathVariable Long id) {
        user.setUserId(id);
        return userService.registerNewUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        if(userService.getUserById(id)==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with Id=" + id +"  Not Found");
        else{
            try {
                userService.deleteUser(id);
//                return new ResponseEntity<>("User deleted successfully",HttpStatus.OK);
                return ResponseEntity.status(HttpStatus.OK).body("user deleted successfully");
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,"User Cannot be Deleted",e);
            }
        }
    }
    @GetMapping("checkRolesOfAllUsers")
    private List<UserAndRoleDTO> userAndRoleDTO(){
        return userService.checkRolesOfAllUsers();
    }
}
