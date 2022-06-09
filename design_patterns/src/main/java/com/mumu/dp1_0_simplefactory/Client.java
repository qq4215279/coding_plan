package com.mumu.dp1_0_simplefactory;

import com.mumu.dp1_0_simplefactory.impl.Chart;
import com.mumu.dp1_0_simplefactory.core.ChartFactory;

public class Client {
	public static void main(String args[]) {
		Chart chart;
		//chart = ChartFactory.getChart("histogram"); // 通过静态工厂方法创建产品

		String type = XMLUtil.getChartType(); // 读取配置文件中的参数
		chart = ChartFactory.getChart(type);  // 创建产品对象

		chart.display();
	}
}
