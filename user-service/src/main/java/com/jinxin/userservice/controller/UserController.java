package com.jinxin.userservice.controller;

import com.jinxin.userservice.dto.UserLoginDTO;
import com.jinxin.userservice.entity.User;
import com.jinxin.userservice.redis.RedisDao;
import com.jinxin.userservice.service.UserServiceDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(value = "测试案例")
public class UserController {

    @Autowired
    private UserServiceDetail userServiceDetail;

    @Autowired
    private RedisDao redisDao;



    @ApiOperation(value = "用户注册",notes = "姓名密码必传",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "username",value = "姓名",required = true),
            @ApiImplicitParam(dataType = "string",name = "password",value = "密码",required = true)
    })
    @PostMapping("/register")
    public User postUser(@RequestParam("username")String username,@RequestParam("password")String password){

        return userServiceDetail.insert(username,password);

    }

    @ApiOperation(value = "用户登录",notes = "姓名密码必传",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "username",value = "姓名",required = true),
            @ApiImplicitParam(dataType = "string",name = "password",value = "密码",required = true)
    })
    @PostMapping("/login")
    public UserLoginDTO login(@RequestParam("username") String username , @RequestParam("password") String password){
        //参数判断，省略
        UserLoginDTO userLoginDTO = userServiceDetail.login(username,password);
        String accessToken = userLoginDTO.getJwt().getAccess_token();
        redisDao.setValue(username,accessToken);
        return userLoginDTO;
    }


    @PostMapping("/delete")
    public String deleteredis(@RequestParam("username") String username){
        redisDao.deletValue(username);
        return "ok";
    }



}
