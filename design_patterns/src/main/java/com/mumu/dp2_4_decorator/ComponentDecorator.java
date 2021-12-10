package com.mumu.dp2_4_decorator;

import com.mumu.dp2_4_decorator.component.Component;

public class ComponentDecorator extends Component {
    // 维持对抽象构件类型对象的引用
    private Component component;

    // 注入抽象构件类型的对象
    public ComponentDecorator(Component component) {
        this.component = component;
    }

    public void display() {
        component.display();
    }
}
