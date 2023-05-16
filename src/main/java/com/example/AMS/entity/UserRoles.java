package com.example.AMS.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @OneToMany()
    @JoinColumn(name="roleId")
    /*referencedColumnName = "permissionId"*/
    private List<RolePermissions> rolePermissions;

    @OneToMany(mappedBy = "userRoles")
    @JsonIgnore
    private List<Users> users;

    @Column(unique = true,length = 50)
    private String roleName;

    private String roleDescription;

}
