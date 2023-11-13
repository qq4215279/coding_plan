/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp2_4_decorator.decorator;

import com.mumu.dp2_4_decorator.component.Component;

/**
 * @author D0381
 */
public abstract class AbstractDecorator extends Component {
    // 维持对抽象构件类型对象的引用
    private Component component;

    // 注入抽象构件类型的对象
    public AbstractDecorator(Component component) {
        this.component = component;
    }

    public void display() {
        component.display();
    }
}
