package com.mumu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class headController {

    @RequestMapping(value = "head",method = RequestMethod.GET)
    public String head(){
        return "栅格系统Demo01";
    }


    @GetMapping("/login")
    public String index(){
        return "login";
    }

}
