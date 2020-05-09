package com.jinxin.userservice.service;

import com.jinxin.userservice.dao.UserDao;
import com.jinxin.userservice.dto.UserLoginDTO;
import com.jinxin.userservice.entity.JWT;
import com.jinxin.userservice.entity.User;
import com.jinxin.userservice.exception.UserLoginException;
import com.jinxin.userservice.feign.AuthServiceClient;
import com.jinxin.userservice.utils.BPwdEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceDetail {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthServiceClient client;

    public User insert(String username,String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(BPwdEncoderUtil.BCryptPassword(password));
        return userDao.save(user);
    }

    public UserLoginDTO login(String username, String password){
        User user=userDao.findByUsername(username);
        if (null == user) {
            throw new UserLoginException("error username");
        }
        if(!BPwdEncoderUtil.matches(password,user.getPassword())){
            throw new UserLoginException("error password");
        }
        // 获取token
        JWT jwt=client.getToken("Basic dXNlci1zZXJ2aWNlOjEyMzQ1Ng==","password",username,password);
        // 获得用户菜单
        if(jwt==null){
            throw new UserLoginException("error internal");
        }
        UserLoginDTO userLoginDTO=new UserLoginDTO();
        userLoginDTO.setJwt(jwt);
        userLoginDTO.setUser(user);
        return userLoginDTO;

    }




}
