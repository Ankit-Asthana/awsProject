package com.example.AMS.entity;

//import com.example.AMS.security.Authority;
import com.example.AMS.repository.UserDao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Data
@RequiredArgsConstructor
public class Users implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRoles userRoles;

    @OneToMany(mappedBy = "users")
    @JsonIgnore
//    @JoinColumn(name = "user_id",referencedColumnName = "userId")
    private List<Tasks> tasks;

    @OneToMany(mappedBy = "users")
    @JsonIgnore
    private List<AssetTransferHistory> assetTransferHistories;

    @OneToMany(mappedBy = "users")
    @JsonIgnore
    private List<AssetAssignment> assetAssignments;
    @Column(unique = true,length = 50)
    private String username;

    @Column(length = 60)
    private String password;
    @Column(length = 50)
    private String fullName;
    @Column(name = "email",unique = true)
    private String email;

    private Boolean status;//1 for active 0 for inactive

    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Temporal(TemporalType.DATE)
    private Date updatedDate;

    private String updatedBy;

    @Temporal(TemporalType.DATE)
    private Date deletedDate;
    private String deletedBy;

//    @Autowired
//    @Transient
//    private UserDao userDao;
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return List.of(new SimpleGrantedAuthority(userRoles.getRoleName()));
//
//        String role=this.getUserRoles().getRoleId().toString();
//            if(role=="1"){
//                this.userRoles.setRoleName("SuperAdmin");
//            }
//            if(role=="2"){
//                this.userRoles.setRoleName("Admin");
//            }
//            if(role=="3"){
//                this.userRoles.setRoleName("Employee");
//            }
        SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(this.getUserRoles().getRoleName());
       // System.out.println(simpleGrantedAuthority);
        return List.of(simpleGrantedAuthority);
//        Set<Authority> set= new HashSet<>();
//        //to get the user roles of the users
//        set.add(new Authority(userRoles.getRoleId()));
//        return null;
//
       // Users user = userDao.findById(userId).get();
//        List<GrantedAuthority> grantedAuthorities=new ArrayList<GrantedAuthority>();
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+this.getUserRoles().getRoleName()));
//       //System.out.println("inside details impl "+grantedAuthorities.get(0).getAuthority());
//        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
