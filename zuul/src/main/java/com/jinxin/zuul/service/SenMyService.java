package com.jinxin.zuul.service;

import com.jinxin.zuul.entity.MySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

/**
 * 将发送消息绑定到自定义管道
 */
@EnableBinding(MySource.class)
public class SenMyService {

    @Autowired
    private MySource mySource;

    //发送的参数也可以是自定义的类而非仅仅是String
    public void sendMyMsg(String username){
        mySource.myOutput().send(MessageBuilder.withPayload(username).build());
    }

}
