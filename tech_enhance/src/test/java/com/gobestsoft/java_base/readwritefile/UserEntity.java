/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.readwritefile;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserEntity
 *
 * @author liuzhen
 * @version 1.0.0 2023/2/8 22:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @ExcelProperty(value = "key")
    private String key;

    @ExcelProperty(value = "value")
    private String value;

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /* public static void main(String[] args) {
        List<UserEntity> dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserEntity userEntity = new UserEntity();
            userEntity.setNa("张三" + i);
            userEntity.setAge(20 + i);
            userEntity.setTime(new Date(System.currentTimeMillis() + i));
            dataList.add(userEntity);
        }
        EasyExcel.write("/Users/lixin/Desktop/easyexcel-user1.xls", UserEntity.class).sheet("用户信息").doWrite(dataList);
    }*/
}
