package com.jinxin.userservice.entity;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 自定义消息消费者管道
 */
public interface MyCustomerChacnnels {

    @Input("myInput")
    SubscribableChannel myInput();


}
