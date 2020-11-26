package com.burhan.missingcallcenter.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Data
@Table(name="Call")
public class CallEntity {
    @Id
    @GeneratedValue
    private String id;

}
