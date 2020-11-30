package com.burhan.missedcallcenter.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@Table(name = "Call")
public class CallEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity callerUserEntity;

    @Column(name = "called_phone", nullable = false)
    private String calledPhone;

    @Column(name = "call_date", nullable = false)
    private Date callDate;

    @Column(name = "not_notified_call_count", nullable = false)
    private int notNotifiedCallCount;

}
