package com.jinxin.zuul.controller;

import com.jinxin.zuul.service.SenMyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    private SenMyService senMyService;

    @Value("${foo}")
    String foo;

    @PostMapping("/delete")
    public String deleteredis(@RequestParam("username") String username){
        senMyService.sendMyMsg(username);
        return "ok";
    }

    @GetMapping("/test")
    public String test(){
        return foo;
    }

}
