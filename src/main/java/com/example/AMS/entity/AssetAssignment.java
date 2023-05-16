package com.example.AMS.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class AssetAssignment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignId;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Assets assets;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @Temporal(TemporalType.DATE)
    private Date issuedDate;
    @Temporal(TemporalType.TIME)
    private Date issuedTime;
    @Temporal(TemporalType.DATE)
    private Date returnDate;
    @Temporal(TemporalType.TIME)
    private Date returnTime;
}
