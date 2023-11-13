/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp3_2_command.test2.commander;

import com.mumu.dp3_2_command.test2.DisplayHelpClass;

public class HelpCommand extends Command {
    // 维持对请求接收者的引用
    private DisplayHelpClass hcObj;

    public HelpCommand() {
        hcObj = new DisplayHelpClass();
    }

    // 命令执行方法，将调用请求接收者的业务方法
    @Override
    public void execute() {
        hcObj.display();
    }
}
