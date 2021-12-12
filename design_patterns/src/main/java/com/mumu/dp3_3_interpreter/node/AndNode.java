/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp3_3_interpreter.node;

// And解释：非终结符表达式
public class AndNode extends AbstractNode {
    // And的左表达式
    private AbstractNode left;
    // And的右表达式
    private AbstractNode right;

    public AndNode(AbstractNode left, AbstractNode right) {
        this.left = left;
        this.right = right;
    }

    // And表达式解释操作
    public String interpret() {
        return left.interpret() + "再" + right.interpret();
    }
}
