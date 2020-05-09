package com.jinxin.uaaservice.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "user_role")
@Entity
public class UserRole implements Serializable {

    @Column(name = "user_id")
    private long userId;

    @Id
    @Column( name = "role_id")
    private long roleId;
}
