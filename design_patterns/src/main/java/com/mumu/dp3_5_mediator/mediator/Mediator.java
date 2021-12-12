/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp3_5_mediator.mediator;

import com.mumu.dp3_5_mediator.colleague.Component;

// 抽象中介者
public abstract class Mediator {
    public abstract void componentChanged(Component c);
}
