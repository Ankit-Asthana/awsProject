package com.example.AMS.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class AssetTypes {
    public enum categoryType{
        Consumable,NonConsumable
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;

    @OneToMany(mappedBy = "assetTypes")
    @JsonIgnore
    private List<Assets> assets;

    private String assetTypeName;

    private categoryType categoryType;

}
