package com.example.AMS.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class RolePermissions {
    public enum permissionType{
        Read,Write,Update,Delete
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;

    @Enumerated(EnumType.STRING)
    private permissionType permissionType;

}
