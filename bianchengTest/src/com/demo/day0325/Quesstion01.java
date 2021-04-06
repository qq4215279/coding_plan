package com.demo.day0325;

import java.util.Scanner;

/**
 * 给一个不多于5为的整数。1.求他是几位数 2.输出每一位数字 3.逆序输出各位数字
 */
public class Quesstion01 { //
    public static void main(String[] args) {
        System.out.println("请输入一个数字");
        Scanner sc=new Scanner(System.in);
        int num =sc.nextInt();
        int e,d,c,b,a;
        e=num/10000;
        d=(num-(e*10000))/1000;
        c=(num-(e*10000+d*1000))/100;
        b=(num-(e*10000+d*1000+c*100))/10;
        a=num-(e*10000+d*1000+c*100+b*10);

        if (e > 0) {
            System.out.println("这是一个五位数");
            System.out.println("万位数字是："+e+" "+"千位数字是："+d+" "+"百位数字是："+c+" "+"十位数字是："+b+" "+"各位数字是："+a);
            int nixu=e+d*10+c*100+b*1000+a*10000;
            System.out.println("该数字逆序时为："+nixu);
        } else if (d > 0) {
            System.out.println("这是一个四位数");
            System.out.println("千位数字是："+d+" "+"百位数字是："+c+" "+"十位数字是："+b+" "+"各位数字是："+a);
            int nixu=d*1+c*10+b*100+a*1000;
            System.out.println("该数字逆序时为："+nixu);
        } else if (c > 0) {
            System.out.println("这是一个三位数");
            System.out.println("百位数字是："+c+" "+"十位数字是："+b+" "+"各位数字是："+a);
            int nixu=c*1+b*10+a*100;
            System.out.println("该数字逆序时为："+nixu);
        } else if (b > 0) {
            System.out.println("这是一个二位数");
            System.out.println("十位数字是："+b+" "+"各位数字是："+a);
            int nixu=b*1+a*10;
            System.out.println("该数字逆序时为："+nixu);
        } else {
            System.out.println("这是一个个位数");
            System.out.println("各位数字是："+a);
            int nixu=a;
            System.out.println("该数字逆序时为它本身："+nixu);
        }
//        System.currentTimeMillis();
          // Thread.currentThread().getName();
    }
}
