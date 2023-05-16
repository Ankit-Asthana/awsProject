package com.example.AMS.service;

//import com.example.AMS.dto.LoginDTO;
import com.example.AMS.dto.UserAndRoleDTO;
import com.example.AMS.dto.UserDTO;
import com.example.AMS.entity.Users;
import com.example.AMS.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public Users registerNewUser(Users users) {
        users.setCreatedDate(new Date());
        System.out.println(passwordEncoder.encode(users.getPassword()));
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return userDao.save(users);
    }

    public List<Users> getAllUsers() {
        return userDao.findAll();
    }

    public Users getUserById(Long userId) {
        return userDao.findById(userId).orElse(null);
    }

    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }

    public boolean userExists(long userId) {
        return userDao.existsById(userId);
    }
    public List<Users> getAllActiveUsers() {
        return userDao.findAllActiveUsers();
    }

    public List<UserDTO> getUsers() {
        return userDao.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO convertEntityToDTO(Users user) {
        UserDTO userDTO=new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUserName(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setCreatedDate(user.getCreatedDate());
        userDTO.setStatus(user.getStatus());
        userDTO.setFullName(user.getFullName());
        userDTO.setRoleName(user.getUserRoles().getRoleName());
        userDTO.setRoleDescription(user.getUserRoles().getRoleDescription());
        return userDTO;
    }
//    public List<LoginDTO> getUserCred() {
//        return userDao.findAll()
//                .stream()
//                .map(this::convertUsersToLoginDTO)
//                .collect(Collectors.toList());
//    }
//    public LoginDTO convertUsersToLoginDTO(Users users){
//        LoginDTO dto=new LoginDTO();
//        dto.setUserId(users.getUserId());
//        dto.setPassword(users.getPassword());
//        dto.setEmail(users.getEmail());
//        dto.setUsername(users.getUsername());
//        dto.setRoleName(users.getUserRoles().getRoleName());
//        return dto;
//    }

    public List<UserAndRoleDTO> checkRolesOfAllUsers() {
        return userDao.findAll()
                .stream()
                .map(this::convertUserRoleEntityToDTO)
                .collect(Collectors.toList());
    }

    private UserAndRoleDTO convertUserRoleEntityToDTO(Users user) {
        UserAndRoleDTO userAndRoleDTO = new UserAndRoleDTO();
        userAndRoleDTO.setUserName(user.getUsername());
        userAndRoleDTO.setUserId(user.getUserId());
        userAndRoleDTO.setFullName(user.getFullName());
        userAndRoleDTO.setStatus(user.getStatus());
        userAndRoleDTO.setUserRole(user.getUserRoles().getRoleName());
        return userAndRoleDTO;
    }

}
