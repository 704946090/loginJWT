package com.jinxin.eurekaserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class cont {

    @RequestMapping("/send/{msg}")
    public String send(@PathVariable("msg") String msg){
        return msg;
    }
}
