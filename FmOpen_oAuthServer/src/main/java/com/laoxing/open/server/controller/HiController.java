package com.laoxing.open.server.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: FmOpen
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-08-04 10:50
 */
@RestController
//@Async //异步的接口
public class HiController {

    @GetMapping("/api/hi.do")
    public String hi(){
        return "OK";
    }
    @GetMapping("/api/hello/hi.do")
    public String hello(){
        return "OK";
    }
}
