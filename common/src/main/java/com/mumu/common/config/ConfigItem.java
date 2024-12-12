
/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.common.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "configItem",
    "configItemInfo"
})
@XmlRootElement(name = "config_item")
public class ConfigItem {

    @XmlElement(name = "config_item")
    protected ConfigItem configItem;
    @XmlElement(name = "config_itemInfo")
    protected List<ConfigItemInfo> configItemInfo;


    /**
     * 获取configItem属性的值。
     */
    public ConfigItem getConfigItem() {
        return configItem;
    }

    public void setConfigItem(ConfigItem value) {
        this.configItem = value;
    }

    public List<ConfigItemInfo> getConfigItemInfo() {
        if (configItemInfo == null) {
            configItemInfo = new ArrayList<ConfigItemInfo>();
        }
        return this.configItemInfo;
    }


}
