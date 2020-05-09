package com.jinxin.uaaservice.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试zuul整合swagger2集成多个微服务
 */
@RestController
public class Swagger2Controller {

    @ApiOperation(value = "接口的功能介绍",notes = "提示接口使用者注意事项",httpMethod = "GET")
    @PostMapping("/hi")
    public String test(){

        return "ok";
    }
}
