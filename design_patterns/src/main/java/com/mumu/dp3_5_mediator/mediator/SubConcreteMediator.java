/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp3_5_mediator.mediator;

import com.mumu.dp3_5_mediator.colleague.Component;
import com.mumu.dp3_5_mediator.colleague.Label;

// 新增具体中介者类
public class SubConcreteMediator extends ConcreteMediator {
    // 增加对Label对象的引用
    public Label label;

    public void componentChanged(Component c) {
        // 单击按钮
        if (c == addButton) {
            System.out.println("--单击增加按钮--");
            list.update();
            cb.update();
            userNameTextBox.update();
            // 文本标签更新
            label.update();
        } else if (c == list) { // 从列表框选择客户
            System.out.println("--从列表框选择客户--");
            cb.select();
            userNameTextBox.setText();
        } else if (c == cb) { // 从组合框选择客户
            System.out.println("--从组合框选择客户--");
            cb.select();
            userNameTextBox.setText();
        }
    }
}
