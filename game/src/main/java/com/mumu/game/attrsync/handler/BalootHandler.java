/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.attrsync.handler;

import com.baloot.game.common.core.BalootTable;
import com.mumu.game.attrsync.constants.EventConstants;
import com.mumu.game.attrsync.enums.ModuleEnum;
import com.mumu.game.attrsync.event.ModuleEvent;
import com.mumu.game.proto.baloot.BalootTableBean;

/**
 * BalootHandler
 *
 * @author liuzhen
 * @version 1.0.0 2024/10/6 17:30
 */
public class BalootHandler<bean extends BalootTableBean> extends AbstractModuleHandler<bean> {

    public BalootHandler(ModuleEnum module) {
        super(module);
    }

    @Override
    public void sync(long playerId) {

    }

    @Override
    public boolean onEvent(ModuleEvent event) {

        BalootTableBean tableBean = new BalootTableBean();
        BalootTable table = (BalootTable)event.args[0];

        switch (event.eventId) {
            case EventConstants.BALOOT_SEND_COMMAND:
                // TODO

                return true;
            default:
                return false;
        }
    }

}
