package com.gobestsoft.controller;

import com.gobestsoft.service.LoginService;
import com.gobestsoft.utils.CookiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;


@Controller
public class LoginController {

    private static final String SUFFIX_PATH = "/bigData";
    private static final String SUFFIX_PATH_MAP = "/bigData/indexmap";

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String login() {
        return SUFFIX_PATH + "/login";
    }

    @GetMapping("/index*")
    public String index() {
        return SUFFIX_PATH + "/index";
    }

    @GetMapping("/indexmap/index.html")
    public String indexMap() {
        return SUFFIX_PATH_MAP + "/index";
    }

    @GetMapping("/indexmap/index-1.html")
    public String index1() {
        return SUFFIX_PATH_MAP + "/index-1";
    }

    @GetMapping("/indexmap/index-2.html")
    public String index2() {
        return SUFFIX_PATH_MAP + "/index-2";
    }

    @GetMapping("/indexmap/index_bk.html")
    public String indexBk() {
        return SUFFIX_PATH_MAP + "/index_bk";
    }

    @GetMapping("/indexmap/data-mobile.html")
    public String dataMobile() {
        return SUFFIX_PATH_MAP + "/data-mobile";
    }

    @GetMapping("/indexmap/data-mobileall.html")
    public String dataMobileAll() {
        return SUFFIX_PATH_MAP + "/data-mobileall";
    }


    @PostMapping("/checkUser")
    @ResponseBody
    public int checkUser(String username, String password, HttpServletResponse response) {

        int res = 0;
        try {
            res = loginService.queryUserByUsernameAndPassword( username, password );
            CookiesUtils.sentToken( username + password, CookiesUtils.Token_Name, response );
        } catch (Exception e) {
            res = 0;
            e.printStackTrace();
        }
        return res;

    }

}
