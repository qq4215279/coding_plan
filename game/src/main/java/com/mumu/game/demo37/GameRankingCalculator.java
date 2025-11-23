/*
 * Copyright 2020-2025, mumu without 996.
 * All Right Reserved.
 */

package com.mumu.game.demo37;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 游戏排名计算器
 */
public class GameRankingCalculator {
    /**
     * 计算玩家排名并设置到Player对象的rank字段
     * @param players 玩家列表
     * @return 返回包含排名信息的玩家列表
     */
    public static List<Player> calculateRanking(List<Player> players) {
        if (players == null || players.isEmpty()) {
            return new ArrayList<>();
        }

        // 创建副本以避免修改原始列表
        List<Player> resultPlayers = new ArrayList<>();
        for (Player player : players) {
            resultPlayers.add(new Player(player.getPlayerId(), player.getOutPlayerCount(), player.getHp()));
        }

        // 1. 按照主要规则排序：血量降序，淘汰数降序
        List<Player> sortedPlayers = resultPlayers.stream()
                .sorted((p1, p2) -> {
                    // 首先按血量降序
                    int hpCompare = Long.compare(p2.getHp(), p1.getHp());
                    if (hpCompare != 0) {
                        return hpCompare;
                    }
                    // 血量相同按淘汰数降序
                    return Integer.compare(p2.getOutPlayerCount(), p1.getOutPlayerCount());
                })
                .collect(Collectors.toList());

        // 2. 计算排名
        List<RankGroup> rankGroups = new ArrayList<>();
        int index = 0;

        while (index < sortedPlayers.size()) {
            // 找到所有血量相同的玩家
            List<Player> sameHpPlayers = findPlayersWithSameHp(sortedPlayers, index);

            if (sameHpPlayers.size() == 1) {
                // 单个玩家，正常排名
                rankGroups.add(new RankGroup(Collections.singletonList(sameHpPlayers.get(0)), false));
                index++;
            } else {
                // 多个玩家血量相同，检查淘汰数情况
                boolean allSameOutCount = sameHpPlayers.stream()
                        .map(Player::getOutPlayerCount)
                        .distinct()
                        .count() == 1;

                if (allSameOutCount) {
                    // 所有淘汰数都相同
                    if (sameHpPlayers.get(0).getOutPlayerCount() > 0) {
                        // 淘汰数>0且相同，高位排名
                        rankGroups.add(new RankGroup(sameHpPlayers, true));
                    } else {
                        // 淘汰数=0且相同，低位排名
                        rankGroups.add(new RankGroup(sameHpPlayers, false));
                    }
                } else {
                    // 淘汰数不同，正常排名
                    for (Player player : sameHpPlayers) {
                        rankGroups.add(new RankGroup(Collections.singletonList(player), false));
                    }
                }
                index += sameHpPlayers.size();
            }
        }

        // 3. 分配具体排名
        assignRanks(rankGroups);

        return resultPlayers;
    }

    /**
     * 分配具体排名
     */
    private static void assignRanks(List<RankGroup> rankGroups) {
        int totalPlayers = rankGroups.stream().mapToInt(g -> g.players.size()).sum();

        // 先处理高位排名组（淘汰数>0且相同的）
        int highRank = 1;
        for (RankGroup group : rankGroups) {
            if (group.isHighRankGroup) {
                for (Player player : group.players) {
                    player.setRank(highRank);
                }
            }
        }

        // 再处理低位排名组（淘汰数=0且相同的）和其他正常组
        int currentRank = 1;
        for (RankGroup group : rankGroups) {
            if (group.isHighRankGroup) {
                // 高位排名组已经处理过，跳过
                continue;
            }

            if (!group.isHighRankGroup && group.players.size() > 1 &&
                    group.players.get(0).getOutPlayerCount() == 0) {
                // 低位排名组：淘汰数=0且相同的，分配低位排名
                int lowRank = totalPlayers - group.players.size() + 1;
                for (Player player : group.players) {
                    player.setRank(lowRank);
                }
                currentRank = lowRank + group.players.size();
            } else {
                // 正常排名组
                for (Player player : group.players) {
                    player.setRank(currentRank);
                    currentRank++;
                }
            }
        }
    }

    /**
     * 从指定位置开始查找所有血量相同的玩家
     */
    private static List<Player> findPlayersWithSameHp(List<Player> players, int startIndex) {
        List<Player> sameHpPlayers = new ArrayList<>();
        long baseHp = players.get(startIndex).getHp();
        sameHpPlayers.add(players.get(startIndex));

        for (int i = startIndex + 1; i < players.size(); i++) {
            if (players.get(i).getHp() == baseHp) {
                sameHpPlayers.add(players.get(i));
            } else {
                break;
            }
        }

        return sameHpPlayers;
    }

    /**
     * 排名组内部类
     */
    private static class RankGroup {
        List<Player> players;
        boolean isHighRankGroup; // true:高位排名, false:低位排名或正常排名

        RankGroup(List<Player> players, boolean isHighRankGroup) {
            this.players = players;
            this.isHighRankGroup = isHighRankGroup;
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        // 测试用例1：高位排名 - 3个玩家血量相同且淘汰数>0相同
        List<Player> players1 = Arrays.asList(
                new Player(1, 2, 100), // 第1名（高位排名）
                new Player(2, 2, 100), // 第1名（高位排名）
                new Player(3, 2, 100), // 第1名（高位排名）
                new Player(4, 1, 90),  // 第4名
                new Player(5, 0, 80)   // 第5名
        );

        System.out.println("测试用例1 - 高位排名（3个第1名）:");
        List<Player> rankedPlayers1 = calculateRanking(players1);
        rankedPlayers1.forEach(System.out::println);

        // 测试用例2：低位排名 - 2个玩家血量相同且淘汰数=0相同
        List<Player> players2 = Arrays.asList(
                new Player(1, 3, 100), // 第1名
                new Player(2, 2, 95),  // 第2名
                new Player(3, 0, 90),  // 第4名（低位排名）
                new Player(4, 0, 90),  // 第4名（低位排名）
                new Player(5, 1, 85)   // 第3名
        );

        System.out.println("\n测试用例2 - 低位排名（2个第4名）:");
        List<Player> rankedPlayers2 = calculateRanking(players2);
        rankedPlayers2.forEach(System.out::println);

        // 测试用例3：混合情况
        List<Player> players3 = Arrays.asList(
                new Player(1, 2, 100), // 第1名（高位排名）
                new Player(2, 2, 100), // 第1名（高位排名）
                new Player(3, 0, 95),  // 第3名
                new Player(4, 0, 90),  // 第5名（低位排名）
                new Player(5, 0, 90)   // 第5名（低位排名）
        );

        System.out.println("\n测试用例3 - 混合情况:");
        List<Player> rankedPlayers3 = calculateRanking(players3);
        rankedPlayers3.forEach(System.out::println);

        // 测试用例4：全部正常排名
        List<Player> players4 = Arrays.asList(
                new Player(1, 3, 100), // 第1名
                new Player(2, 2, 95),  // 第2名
                new Player(3, 1, 90),  // 第3名
                new Player(4, 0, 85),  // 第4名
                new Player(5, 0, 80)   // 第5名
        );

        System.out.println("\n测试用例4 - 全部正常排名:");
        List<Player> rankedPlayers4 = calculateRanking(players4);
        rankedPlayers4.forEach(System.out::println);

        // 测试用例5：极端情况 - 所有玩家都高位排名
        List<Player> players5 = Arrays.asList(
                new Player(1, 2, 100), // 第1名
                new Player(2, 2, 100), // 第1名
                new Player(3, 2, 100), // 第1名
                new Player(4, 2, 100), // 第1名
                new Player(5, 2, 100)  // 第1名
        );

        System.out.println("\n测试用例5 - 全部高位排名:");
        List<Player> rankedPlayers5 = calculateRanking(players5);
        rankedPlayers5.forEach(System.out::println);
    }
}