package com.mumu.dp3_9_strategy;

import com.mumu.dp3_9_strategy.discount.Discount;

public class Client {
    public static void main(String args[]) {
        MovieTicket mt = new MovieTicket();
        double originalPrice = 60.0;
        double currentPrice;

        mt.setPrice(originalPrice);
        System.out.println("原始价为：" + originalPrice);
        System.out.println("---------------------------------");

        Discount discount;
        // 读取配置文件并反射生成具体折扣对象
        discount = (Discount)XMLUtil.getBean();
        // 注入折扣对象
        mt.setDiscount(discount);

        currentPrice = mt.getPrice();
        System.out.println("折后价为：" + currentPrice);
    }
}
