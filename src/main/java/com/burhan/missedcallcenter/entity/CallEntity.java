package com.burhan.missedcallcenter.entity;


import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name="Call")
public class CallEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity callerUserEntity;

    private String calledPhone;

    @Column(name = "not_notified_call_count", nullable = false)
    private int notNotifiedCallCount;

}
