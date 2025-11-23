/*
 * Copyright 2020-2025, mumu without 996.
 * All Right Reserved.
 */

package com.mumu.game.demo37;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GameRankingCalculator2
 *
 * @author liuzhen
 * @version 1.0.0 2025/11/23 19:02
 */
public class GameRankingCalculator2 {

    /*
    帮我用java写一个5个玩家游戏战斗后的排名算法，规则如下：
    - 剩余血量最多的玩家为第1名，只个按照利会血量从家到少进行排名递增
    - 若存在n名玩家剩余血量相同的情况，则按照淘汰玩家数进行名次条件判定，淘汰玩家数重多者为名次靠前者。
        - 若淘汰玩家数相同且不为0，则n名玩家均为第1名。
        - 若淘汰玩家数均为0，则所有玩家判定为第5名。

* */
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
                }).collect(Collectors.toList());



        for (int index = 0; index < sortedPlayers.size();) {
            // 找到所有血量相同的玩家
            Tuple3<List<Player>, Integer, Integer> pair = findPlayersWithSameHpAndOutCount(sortedPlayers, index);
            List<Player> tempPlayerList = pair.getO1();
            int rank = pair.getO2();

            for (Player player : tempPlayerList) {
                player.setRank(rank);
            }

            index = pair.getO3();
        }

        return sortedPlayers;
    }

    /**
     * 从指定位置开始查找所有血量相同的玩家
     */
    private static Tuple3<List<Player>, Integer, Integer> findPlayersWithSameHpAndOutCount(List<Player> players,
                                                                                           int startIndex) {
        int rank = startIndex + 1;

        List<Player> sameHpPlayers = new ArrayList<>();
        Player player = players.get(startIndex);
        sameHpPlayers.add(player);

        long baseOupHp = player.getHp();
        long baseOupPlayerCount = player.getOutPlayerCount();

        for (int i = startIndex + 1; i < players.size(); i++) {
            Player targetPlayer = players.get(i);
            if (targetPlayer.getOutPlayerCount() == baseOupPlayerCount && targetPlayer.getHp() == baseOupHp) {
                sameHpPlayers.add(targetPlayer);
            } else {
                break;
            }
        }

        if (sameHpPlayers.size() == 1) {
            return new Tuple3<>(sameHpPlayers, rank, rank);
        }

        if (baseOupPlayerCount == 0) {
            rank = startIndex + sameHpPlayers.size();
        }

        return new Tuple3<>(sameHpPlayers, rank, startIndex + sameHpPlayers.size());
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

    public static class Tuple3<T1, T2, T3> {
        public T1 o1;
        public T2 o2;
        public T3 o3;

        public Tuple3(T1 o1, T2 o2, T3 o3) {
            this.o1 = o1;
            this.o2 = o2;
            this.o3 = o3;
        }

        public T1 getO1() {
            return o1;
        }

        public T2 getO2() {
            return o2;
        }

        public T3 getO3() {
            return o3;
        }
    }
}
