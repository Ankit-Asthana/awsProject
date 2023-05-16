package com.example.AMS.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class AssetTransferHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Assets assets;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
}
