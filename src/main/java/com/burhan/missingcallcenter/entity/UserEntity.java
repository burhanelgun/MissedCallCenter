package com.burhan.missingcallcenter.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="User")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = true)
    private String phone;

    @Column(name = "password", nullable = false)
    private String password;


}
