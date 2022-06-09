package com.mumu.dp1_0_simplefactory.core;

import com.mumu.dp1_0_simplefactory.impl.Chart;
import com.mumu.dp1_0_simplefactory.impl.HistogramChart;
import com.mumu.dp1_0_simplefactory.impl.LineChart;
import com.mumu.dp1_0_simplefactory.impl.PieChart;

// 图表工厂类，充当工厂类
public class ChartFactory {
    // 静态工厂方法
    public static Chart getChart(String type) {
        Chart chart = null;
        if (type.equalsIgnoreCase("histogram")) {
            chart = new HistogramChart();
            System.out.println("初始化设置柱状图！");
        } else if (type.equalsIgnoreCase("pie")) {
            chart = new PieChart();
            System.out.println("初始化设置饼状图！");
        } else if (type.equalsIgnoreCase("line")) {
            chart = new LineChart();
            System.out.println("初始化设置折线图！");
        }
        return chart;
    }
}
