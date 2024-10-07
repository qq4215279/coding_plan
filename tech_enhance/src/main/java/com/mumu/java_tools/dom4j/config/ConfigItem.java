
package com.mumu.java_tools.dom4j.config;

import java.util.ArrayList;
import java.util.List;


public class ConfigItem {

    protected ConfigItem configItem;
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
