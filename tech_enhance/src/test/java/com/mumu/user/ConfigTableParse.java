package com.mumu.user;

import java.util.HashMap;
import java.util.Map;

/**
 * ConfigTableParse
 *
 * @author liuzhen
 * @version 1.0.0 2024/4/16 19:09
 */
public class ConfigTableParse {
    public static void main(String[] args) {
        String[] split = s.split("\n");

        Map<String, String> subTableNameExcelNameMap = new HashMap<>();


        for (String line : split) {
            String[] split1 = line.split(" ");
            String excelName = split1[0];
            String subTableName = split1[3];
            String defaultFieldName = split1[5];

            String key = subTableName + defaultFieldName;

            String orDefault = subTableNameExcelNameMap.get(key);
            if (subTableNameExcelNameMap.containsKey(key) && orDefault != null && orDefault.equals(excelName)) {
                System.out.println("子表名重复");
            }

            subTableNameExcelNameMap.put(key, excelName);
        }
    }

    private static String s = "excelName: config_fishery subTableName: config_fishery_base defaultFieldName: describe\n" +
            "excelName: config_fishery subTableName: config_fishery_base defaultFieldName: name\n" +
            "excelName: config_item subTableName: config_item defaultFieldName: typeName\n" +
            "excelName: config_item subTableName: config_item defaultFieldName: unit\n" +
            "excelName: config_item subTableName: config_item defaultFieldName: describe\n" +
            "excelName: config_item subTableName: config_item defaultFieldName: effectDescribe\n" +
            "excelName: config_item subTableName: config_item_suit defaultFieldName: describe1\n" +
            "excelName: config_item subTableName: config_item_suit defaultFieldName: describe2\n" +
            "excelName: config_item subTableName: config_item defaultFieldName: extraIconName\n" +
            "excelName: config_item subTableName: config_item defaultFieldName: name\n" +
            "excelName: config_item subTableName: config_item_cannon defaultFieldName: describe1\n" +
            "excelName: config_mail subTableName: config_mail defaultFieldName: content\n" +
            "excelName: config_mail subTableName: config_mail defaultFieldName: name\n" +
            "excelName: config_missile subTableName: config_sea_demon_jacketpot defaultFieldName: name\n" +
            "excelName: config_shop subTableName: config_package defaultFieldName: name\n" +
            "excelName: config_shop subTableName: config_winTreasureReward defaultFieldName: rewardName\n" +
            "excelName: config_shop subTableName: config_VipPrivilege_package defaultFieldName: name\n" +
            "excelName: config_shop subTableName: config_shopBankrupt defaultFieldName: name\n" +
            "excelName: config_shop subTableName: config_shopTurret defaultFieldName: name_tw\n" +
            "excelName: config_shop subTableName: config_shopBankrupt defaultFieldName: againDescribe\n" +
            "excelName: config_shop subTableName: config_shopSpecial defaultFieldName: name\n" +
            "excelName: config_shop subTableName: config_shopBankrupt defaultFieldName: firstDescribe\n" +
            "excelName: config_shop subTableName: config_shopGold defaultFieldName: name\n" +
            "excelName: config_shop subTableName: config_change_package defaultFieldName: describe\n" +
            "excelName: config_shop subTableName: config_shopMember defaultFieldName: name\n" +
            "excelName: config_shop subTableName: config_shopItem defaultFieldName: describe\n" +
            "excelName: config_shop subTableName: config_package defaultFieldName: packageDescribe\n" +
            "excelName: config_shop subTableName: config_carnival_package defaultFieldName: name\n" +
            "excelName: config_shop subTableName: config_monthCard defaultFieldName: name\n" +
            "excelName: config_shop subTableName: config_good_id defaultFieldName: nameHead\n" +
            "excelName: config_shop subTableName: config_shopBankrupt defaultFieldName: nameHead\n" +
            "excelName: config_shop subTableName: config_shopBankrupt defaultFieldName: bankruptDescribe\n" +
            "excelName: config_shop subTableName: config_shopTurret defaultFieldName: describe\n" +
            "excelName: config_shop subTableName: config_shopMember defaultFieldName: describe\n" +
            "excelName: config_shop subTableName: config_shopMember defaultFieldName: svipDescribe\n" +
            "excelName: config_shop subTableName: config_token defaultFieldName: name\n" +
            "excelName: config_shop subTableName: config_shopMember defaultFieldName: addDescribe\n" +
            "excelName: config_shop subTableName: config_shopFlashSale defaultFieldName: name\n" +
            "excelName: config_shop subTableName: config_good_id defaultFieldName: againDescribe\n" +
            "excelName: config_shop subTableName: config_shopSpecial defaultFieldName: describe\n" +
            "excelName: config_shop subTableName: config_battle_reward defaultFieldName: targetDescribe\n" +
            "excelName: config_shop subTableName: config_treasure defaultFieldName: name\n" +
            "excelName: config_shop subTableName: config_shopTurret defaultFieldName: name\n" +
            "excelName: config_shop subTableName: config_shopItem defaultFieldName: name\n" +
            "excelName: config_shop subTableName: config_shopDiamond defaultFieldName: nameHead\n" +
            "excelName: config_shop subTableName: config_good_id defaultFieldName: firstDescribe\n" +
            "excelName: config_shop subTableName: config_rechargePackage defaultFieldName: name\n" +
            "excelName: config_shop subTableName: config_change_package defaultFieldName: name\n" +
            "excelName: config_shop subTableName: config_QQhall_goodPic defaultFieldName: name\n" +
            "excelName: config_vip subTableName: config_vip_magnate_plan defaultFieldName: name\n" +
            "excelName: config_vip subTableName: config_vip_task defaultFieldName: taskName\n" +
            "excelName: config_vip subTableName: config_vip_magnate_plan defaultFieldName: description\n" +
            "excelName: config_vip subTableName: config_vip_task defaultFieldName: description\n" +
            "excelName: config_vip subTableName: config_vipDescribe defaultFieldName: Describe\n" +
            "excelName: config_vip subTableName: config_vip_exclusive_control defaultFieldName: name\n" +
            "excelName: config_vip subTableName: config_vip_integral_shop defaultFieldName: name\n" +
            "excelName: config_vip subTableName: config_vip_patent defaultFieldName: decribe1\n" +
            "excelName: config_vip subTableName: config_vip_treasury defaultFieldName: name\n" +
            "excelName: config_vip subTableName: config_vipDescribe defaultFieldName: Describe2\n" +
            "excelName: config_vip subTableName: config_vip_special_item defaultFieldName: name\n" +
            "excelName: config_vip subTableName: config_vip_patent_describe defaultFieldName: decribe\n" +
            "excelName: config_vip subTableName: config_vip_patent defaultFieldName: decribe2\n" +
            "excelName: config_rank subTableName: config_rank_captain_reward defaultFieldName: description\n" +
            "excelName: config_rank subTableName: config_rank_xiaKe_3d defaultFieldName: description\n" +
            "excelName: config_rank subTableName: config_rank_time defaultFieldName: description\n" +
            "excelName: config_regulate subTableName: config_regulate_share defaultFieldName: describe\n" +
            "excelName: config_regulate subTableName: config_function_open_control defaultFieldName: describe\n" +
            "excelName: config_regulate subTableName: config_function_open_control defaultFieldName: module_name\n" +
            "excelName: config_regulate subTableName: config_regulate_problem defaultFieldName: answerContent\n" +
            "excelName: config_regulate subTableName: config_regulate_share defaultFieldName: describe2\n" +
            "excelName: config_regulate subTableName: config_regulate_problem defaultFieldName: askContent\n" +
            "excelName: config_regulate subTableName: config_regulate_main defaultFieldName: name\n" +
            "excelName: config_regulate subTableName: config_function_hall_control defaultFieldName: module_name\n" +
            "excelName: config_regulate subTableName: config_regulate_rank defaultFieldName: rankDescribe\n" +
            "excelName: config_regulate subTableName: config_regulate_cannon defaultFieldName: name\n" +
            "excelName: config_buff subTableName: config_card defaultFieldName: showMessage\n" +
            "excelName: config_buff subTableName: config_card_buff defaultFieldName: name\n" +
            "excelName: config_buff subTableName: config_card_buff defaultFieldName: message\n" +
            "excelName: config_buff subTableName: config_card defaultFieldName: name\n" +
            "excelName: config_regression subTableName: config_threeCountries_rank defaultFieldName: description\n" +
            "excelName: config_regression subTableName: config_regression_level defaultFieldName: name\n" +
            "excelName: config_regression subTableName: config_regression_level defaultFieldName: tips\n" +
            "excelName: config_regression subTableName: config_regression_sundry defaultFieldName: value\n" +
            "excelName: config_regression subTableName: config_threeCountries_general defaultFieldName: describe\n" +
            "excelName: config_regression subTableName: config_threeCountries_official defaultFieldName: name\n" +
            "excelName: config_regression subTableName: config_regression_sundry defaultFieldName: describe\n" +
            "excelName: config_regression subTableName: config_regression_task defaultFieldName: description\n" +
            "excelName: config_regression subTableName: config_threeCountries_general defaultFieldName: name\n" +
            "excelName: config_regression subTableName: config_threeCountries_general defaultFieldName: heroProfile\n" +
            "excelName: config_regression subTableName: config_regression_task defaultFieldName: sheetName\n" +
            "excelName: config_regression subTableName: config_threeCountries_general defaultFieldName: skillDes\n" +
            "excelName: config_fish_third subTableName: config_nation_task defaultFieldName: description\n" +
            "excelName: config_fish_third subTableName: config_nation_rank defaultFieldName: description\n" +
            "excelName: config_dragon_city subTableName: config_free_active_task defaultFieldName: description\n" +
            "excelName: config_dragon_city subTableName: config_free_active_shop defaultFieldName: desc\n" +
            "excelName: config_artifact subTableName: config_artifact_base defaultFieldName: name\n" +
            "excelName: config_artifact subTableName: config_medal_main defaultFieldName: name\n" +
            "excelName: config_artifact subTableName: config_medal_main defaultFieldName: effect\n" +
            "excelName: config_artifact subTableName: config_artifact_base defaultFieldName: describe1\n" +
            "excelName: config_artifact subTableName: config_artifact_base defaultFieldName: describe\n" +
            "excelName: config_artifact subTableName: config_medal_main defaultFieldName: des\n" +
            "excelName: config_artifact subTableName: config_artifact_jump defaultFieldName: des\n" +
            "excelName: config_festival subTableName: config_festival_task defaultFieldName: description\n" +
            "excelName: config_festival subTableName: config_newyear_treasure defaultFieldName: unlockDes\n" +
            "excelName: config_festival subTableName: config_festival_mermaid defaultFieldName: name\n" +
            "excelName: config_festival subTableName: config_festival_goddess_gift defaultFieldName: name\n" +
            "excelName: config_festival subTableName: config_festival_star defaultFieldName: starWord\n" +
            "excelName: config_festival subTableName: config_festival_goddess_gift defaultFieldName: res\n" +
            "excelName: config_festival subTableName: config_dragon_task defaultFieldName: description\n" +
            "excelName: config_festival subTableName: config_festival_double_gift defaultFieldName: name\n" +
            "excelName: config_festival subTableName: config_nationday_welfare defaultFieldName: title\n" +
            "excelName: config_festival subTableName: config_wealth_reward defaultFieldName: name\n" +
            "excelName: config_festival subTableName: config_festival_cum_sign defaultFieldName: desc\n" +
            "excelName: config_festival subTableName: config_festival_group_buy defaultFieldName: name\n" +
            "excelName: config_festival subTableName: config_festival_crazy_weekend defaultFieldName: name\n" +
            "excelName: config_festival subTableName: config_festival_danDetail defaultFieldName: name\n" +
            "excelName: config_festival subTableName: config_festival_giftbuy_reward defaultFieldName: describe\n" +
            "excelName: config_festival subTableName: config_festival_shop defaultFieldName: dec\n" +
            "excelName: config_festival subTableName: config_festival_dan defaultFieldName: name\n" +
            "excelName: config_festival subTableName: config_free_shopping defaultFieldName: name\n" +
            "excelName: config_festival subTableName: config_festival_rank defaultFieldName: description\n" +
            "excelName: config_festival subTableName: config_festival_free defaultFieldName: name\n" +
            "excelName: config_festival subTableName: config_christmas_gift defaultFieldName: name\n" +
            "excelName: config_festival subTableName: config_nationday_treasure defaultFieldName: name\n" +
            "excelName: config_navigation subTableName: config_navigation_hero defaultFieldName: des\n" +
            "excelName: config_navigation subTableName: config_navigation_sundry defaultFieldName: value\n" +
            "excelName: config_navigation subTableName: config_navigation_travel defaultFieldName: name\n" +
            "excelName: config_navigation subTableName: config_navigation_travel defaultFieldName: des\n" +
            "excelName: config_navigation subTableName: config_navigation_task defaultFieldName: description\n" +
            "excelName: config_navigation subTableName: config_navigation_hero defaultFieldName: name\n" +
            "excelName: config_navigation subTableName: config_navigation_cannon defaultFieldName: name\n" +
            "excelName: config_navigation subTableName: config_navigation_picture defaultFieldName: name\n" +
            "excelName: config_navigation subTableName: config_navigation_map defaultFieldName: name\n" +
            "excelName: config_navigation subTableName: config_navigation_battle defaultFieldName: des\n" +
            "excelName: config_navigation subTableName: config_navigation_cannon defaultFieldName: des\n" +
            "excelName: config_festival_common subTableName: config_festival_control defaultFieldName: name\n" +
            "excelName: config_equipment subTableName: config_equip_avatar_main defaultFieldName: name\n" +
            "excelName: config_equipment subTableName: config_equip_aircraft_main defaultFieldName: skillDes\n" +
            "excelName: config_equipment subTableName: config_equip_avatar_main defaultFieldName: des\n" +
            "excelName: config_equipment subTableName: config_equip_aircraft_main defaultFieldName: coinDes\n" +
            "excelName: config_assistant_season subTableName: config_season_warOrder_type defaultFieldName: typeName\n" +
            "excelName: config_assistant_season subTableName: config_season_lottery defaultFieldName: des\n" +
            "excelName: config_assistant_season subTableName: config_season_map defaultFieldName: mapName\n" +
            "excelName: config_assistant_season subTableName: config_season_confidant defaultFieldName: des\n" +
            "excelName: config_assistant_season subTableName: config_season_shop defaultFieldName: desc\n" +
            "excelName: config_assistant_season subTableName: config_season_confidant defaultFieldName: name\n" +
            "excelName: config_assistant_season subTableName: config_season_shed defaultFieldName: name\n" +
            "excelName: config_fairy_land subTableName: config_fairy_task defaultFieldName: description\n" +
            "excelName: config_fairy_land subTableName: config_fairy_shop defaultFieldName: des\n" +
            "excelName: config_fairy_land subTableName: config_fairy_shop defaultFieldName: title\n" +
            "excelName: config_fairy_land subTableName: config_fairy_sundry defaultFieldName: describe\n" +
            "excelName: config_fairy_land subTableName: config_fairy_jump defaultFieldName: title\n" +
            "excelName: config_fairy_land subTableName: config_fairy_skill defaultFieldName: name\n" +
            "excelName: config_fairy_land subTableName: config_fairy_jump defaultFieldName: exchangeDes\n" +
            "excelName: config_fairy_land subTableName: config_fairy_level defaultFieldName: name\n" +
            "excelName: config_fairy_land subTableName: config_fairy_skill defaultFieldName: des\n" +
            "excelName: config_fairy_land subTableName: config_fairy_pellet defaultFieldName: name\n" +
            "excelName: config_fairy_land subTableName: config_fairy_jump defaultFieldName: des\n" +
            "\n";
}
