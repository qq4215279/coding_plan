/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp3_2_command.test2.commander;

import com.mumu.dp3_2_command.SystemExitClass;

public class ExitCommand extends Command {
    // 维持对请求接收者的引用
    private SystemExitClass seObj;

    public ExitCommand() {
        seObj = new SystemExitClass();
    }

    // 命令执行方法，将调用请求接收者的业务方法
    public void execute() {
        seObj.exit();
    }
}
