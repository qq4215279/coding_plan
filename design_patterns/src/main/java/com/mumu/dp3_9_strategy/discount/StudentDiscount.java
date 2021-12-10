package com.mumu.dp3_9_strategy.discount;

// 学生票折扣类：具体策略类
public class StudentDiscount implements Discount {
    private final double DISCOUNT = 0.8;

    public double calculate(double price) {
        System.out.println("学生票：");
        return price * DISCOUNT;
    }
}
