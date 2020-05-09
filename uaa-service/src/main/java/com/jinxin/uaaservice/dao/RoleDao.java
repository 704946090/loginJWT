package com.jinxin.uaaservice.dao;

import com.jinxin.uaaservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,Long> {
    Role findById(long id);
}
