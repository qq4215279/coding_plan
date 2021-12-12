/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp3_5_mediator.colleague;

import com.mumu.dp3_5_mediator.mediator.Mediator;

// 抽象组件类：抽象同事类
public abstract class Component {
    protected Mediator mediator;

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    // 转发调用
    public void changed() {
        mediator.componentChanged(this);
    }

    public abstract void update();
}
