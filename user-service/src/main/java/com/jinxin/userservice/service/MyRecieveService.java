package com.jinxin.userservice.service;


import com.jinxin.userservice.entity.MyCustomerChacnnels;
import com.jinxin.userservice.redis.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(MyCustomerChacnnels.class)
public class MyRecieveService {

    @Autowired
    private MyCustomerChacnnels customerChacnnels;

    @Autowired
    private RedisDao redisDao;

    @StreamListener("myInput")
    public void recieve(String username){
        if(username!=null && !"".equals(username)){
            System.out.println("myinput :"+username);
            redisDao.deletValue(username);
        }else {
            System.out.println("username:"+username+"is empty.");
        }

    }

}
