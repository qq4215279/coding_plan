/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.study.game.test.service;

import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 *
 * @author liuzhen
 * @version 1.0.0 2024/12/12 22:11
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public void print() {
        System.out.println("成功从spring中获取到对象...");
    }
}
