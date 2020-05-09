package com.jinxin.zuul.entity;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 *自定义消息生产者管道
 */
public interface MySource {

    //自定义管道名称为"myOutput",并添加到application.yml文件
    @Output("myOutput")
    MessageChannel myOutput();
}
