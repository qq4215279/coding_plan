/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.attrsync.handler;

import com.mumu.game.attrsync.constants.EventConstants;
import com.mumu.game.attrsync.enums.ModuleEnum;
import com.mumu.game.attrsync.event.ModuleEvent;

/**
 * ItemHandler
 *
 * @author liuzhen
 * @version 1.0.0 2024/10/6 17:30
 */
public class ItemHandler extends AbstractModuleHandler {

    public ItemHandler(ModuleEnum module) {
        super(module);
    }

    @Override
    public void sync(long playerId) {

    }

    @Override
    public boolean onEvent(ModuleEvent event) {
        // TODO

        switch (event.eventId) {
            case EventConstants.CHANGE_ITEM:
                // TODO

                return true;
            default:
                return false;
        }
    }
}
