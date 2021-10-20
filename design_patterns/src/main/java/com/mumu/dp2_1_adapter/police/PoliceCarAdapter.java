package com.mumu.dp2_1_adapter.police;

import com.mumu.dp2_1_adapter.CarController;

// 警车适配器，充当适配器
public class PoliceCarAdapter extends CarController {
    private PoliceSound sound;  // 定义适配者PoliceSound对象
    private PoliceLamp lamp;   // 定义适配者PoliceLamp对象

    public PoliceCarAdapter() {
        sound = new PoliceSound();
        lamp = new PoliceLamp();
    }

    // 发出警笛声音
    public void phonate() {
        // 调用适配者类PoliceSound的方法
        sound.alarmSound();
    }

    // 呈现警灯闪烁
    public void twinkle() {
        // 调用适配者类PoliceLamp的方法
        lamp.alarmLamp();
    }
}
