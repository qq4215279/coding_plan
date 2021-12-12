/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp3_2_command.test1.calculator;

import com.mumu.dp3_2_command.test1.calculator.commander.AbstractCommand;
import com.mumu.dp3_2_command.test1.calculator.commander.AddCommand;

public class Client {
    public static void main(String args[]) {
        CalculatorForm form = new CalculatorForm();
        AbstractCommand command;
        command = new AddCommand();
        // 向发送者注入命令对象
        form.setCommand(command);

        form.compute(10);
        form.compute(5);
        form.compute(10);
        // 撤销
        form.undo();
    }
}
