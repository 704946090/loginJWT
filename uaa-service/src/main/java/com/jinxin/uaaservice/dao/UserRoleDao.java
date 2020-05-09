package com.jinxin.uaaservice.dao;

import com.jinxin.uaaservice.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleDao extends JpaRepository<UserRole,Long> {
    List<UserRole> findByUserId(Long userId);
}
