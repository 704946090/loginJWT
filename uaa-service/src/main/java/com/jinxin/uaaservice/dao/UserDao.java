package com.jinxin.uaaservice.dao;

import com.jinxin.uaaservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {
    User findByUsername(String username);
//    User findByUsernameAndIdNotLike(String username,long Id);

//    @Query(value = "select * from user u where u.username =?1 ")
//    User findByUsernames(String username);
}
