/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.comparator;

import com.mumu.common.pojo.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ComparatorApiDemo
 *
 * @author liuzhen
 * @version 1.0.0 2023/6/14 14:18
 */
public class ComparatorApiDemo {


    @Test
    public void test01() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("munu", 100));
        userList.add(new User("tata", 80));
        userList.add(new User("lili", 900));

        // 1. 升序
        // userList = userList.stream().sorted(Comparator.comparingInt(User::getAge)).collect(Collectors.toList());
        // userList = userList.stream().sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList());

        // 2. 降序
        // userList = userList.stream().sorted(Comparator.comparingInt(User::getAge).reversed()).collect(Collectors.toList());
        // userList = userList.stream().sorted(Comparator.comparing(User::getAge, Comparator.reverseOrder())).collect(Collectors.toList());
        userList = userList.stream().sorted(Comparator.comparing(User::getAge, Comparator.reverseOrder()))
                .collect(Collectors.toList());

        System.out.println(userList.toString());
    }

}
