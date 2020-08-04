package com.laoxing.open.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: FmOpen
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-08-04 11:04
 */
@RestController
public class OrderController {
    @GetMapping("api/order/detail.do")
    public String detail(int id){
        return "欢迎---"+id;
    }
}
