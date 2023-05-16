package com.example.AMS.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Assets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assetId;
    @OneToMany(mappedBy = "assets")
    @JsonIgnore
    private List<AssetAssignment> assetAssignments;
    @OneToMany(mappedBy = "assets")
    @JsonIgnore
    private List<AssetTransferHistory> assetTransferHistories;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private AssetTypes assetTypes;

    @OneToOne(mappedBy = "assets")
    @JsonIgnore
    private Tasks tasks;
    private Long serialNo;
    private String modelName;
    private String manufacturer;
    private Boolean isAssigned;

    @Lob
    @Column(length = 1000)
    private byte[] purchaseInvoice;


    @Temporal(TemporalType.DATE)
    private Date createdDate;

    private String createdBy;

    @Temporal(TemporalType.DATE)
    private Date updatedDate;
    private String updatedBy;
    @Temporal(TemporalType.DATE)
    private Date deletedDate;
    private String deletedBy;
}
