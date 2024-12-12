/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.study.game.test.service.UserService;

// 测试启动器，并加载Spring Boot测试注解
@RunWith(SpringRunner.class)
// 标记为Spring Boot单元测试类，并加载项目的ApplicationContext上下文环境
@SpringBootTest
public class SpringbootDemoApplicationTests {

    @Resource
    private UserService userService;

    // 自动创建的单元测试方法实例
    @Test
    public void contextLoads() {
        userService.print();
    }
}