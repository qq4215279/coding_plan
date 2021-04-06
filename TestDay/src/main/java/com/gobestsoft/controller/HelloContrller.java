package com.gobestsoft.controller;

import com.gobestsoft.pojo.User;
import com.gobestsoft.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloContrller {
    
    @Autowired
    HelloService helloService;


    @GetMapping("hello")
    public String testHello(){
        System.out.println("hello world");
        return "hello world";


    }

    @GetMapping("query")
    public List<User> testQuery(){

        int mem_id = 1178381;
        List<User> list = this.helloService.testQuery( mem_id );
        System.out.println("user"+list);
        return list;
    }

    @GetMapping("update")
    public void updateQuestion(){
        helloService.updateQuestion();
//        return ResponseEntity.status( HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("update2")
    public void updateQuestion2(){

        helloService.updateQuestion2();

    }

}
