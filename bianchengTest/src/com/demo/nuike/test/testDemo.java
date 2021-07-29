/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.nuike.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class testDemo {

    /** 请完成下面这个函数，实现题目要求的功能 **/
    /**
     * 当然，你也可以不按照这个模板来作答，完全按照自己的想法来
     **/
    public static String measureDistance(List<Double> xList, List<Double> yList, double x, double y) {

        Double x1 = xList.get( 0 );
        Double x2 = xList.get( 1 );
        Double x3 = xList.get( 2 );
        Double x4 = xList.get( 3 );
        Double y1 = xList.get( 0 );
        Double y2 = xList.get( 1 );
        Double y3 = xList.get( 2 );
        Double y4 = xList.get( 3 );

        if ((x > x1 && y < y1) && (x < x2 && y < y2) && (x < x3 && x < y3)&&(x > x4 && y >y4 )){

            return "yes 0";
        }else {
            return "no 1";
        }

    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        //(x,y)为小广所在的位置
        double x = Double.parseDouble(line.split(",")[0]);
        double y = Double.parseDouble(line.split(",")[1]);

        line = in.nextLine();
        //xList记录了多边形n个点的x坐标,yList记录了多边形n个点的y坐标
        List<Double> xList = new ArrayList<>();
        List<Double> yList = new ArrayList<>();
        String[] array = line.split(",");
        for(int i = 0; i < array.length; i++) {
            xList.add(Double.parseDouble(array[i]));
            yList.add(Double.parseDouble(array[i+1]));
            i++;
        }
        in.close();
        System.out.println(measureDistance(xList, yList, x, y));
    }
}
