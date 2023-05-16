package com.example.AMS.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Tasks {
    public enum taskType{
        Request,Complaint
    }
    public enum status{
        Open,Review,Approved,Rejected
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    @OneToOne
    @JoinColumn(name = "asset_id")
    private Assets assets;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @Enumerated(EnumType.STRING)
    private taskType taskType;

    @Lob
    @Column(length = 1000)
    private byte[] image;
    private String description;

    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Enumerated(EnumType.STRING)
    private status status;

}
