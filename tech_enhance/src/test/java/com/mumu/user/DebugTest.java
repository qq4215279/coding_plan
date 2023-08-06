/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.user;

import com.mumu.common.pojo.User;
import org.junit.Test;

/**
 * DebugTest
 * 断点test
 * @author liuzhen
 * @version 1.0.0 2021/7/28 14:51
 */
public class DebugTest {

    /**
     * 使用技巧：
     *  1. Breakpoints -> Java Exception Breakpoints -> 添加去掉勾选 Any exception
     *  -> 添加对应异常，eg：NullPointException,ArthmeticException,...
     *  2. debug 启动
     */
    @Test
    public void debugTest() {
        User user1 = createUser("xiaoming", 10);
        User user2 = createUser();
        User user3 = createUser("lihua", 30);

        User[] arr = {user1, user2, user3};

        for (int i = 0; i < 4; i++) {
            int aa = (int)(Math.random() * 100);

            User user = null;
            if (aa / 2 == 0) {
                user = createUser("name" + aa, aa);
            } else {
                user = createUser();
            }

            print(user);
        }

    }

    private void print(User user) {
        String name = user.getUserName();
        int age = user.getAge();

        System.out.println(user);
    }

    public User createUser(String name, int age) {
        return new User(name, age);
    }

    public User createUser() {
        return new User();
    }

}
