package com.jinxin.userservice.dto;

import com.jinxin.userservice.entity.JWT;
import com.jinxin.userservice.entity.User;
import lombok.Data;


@Data
public class UserLoginDTO {

    private JWT jwt;

    private User user;

    public JWT getJwt() {
        return jwt;
    }

    public void setJwt(JWT jwt) {
        this.jwt = jwt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
