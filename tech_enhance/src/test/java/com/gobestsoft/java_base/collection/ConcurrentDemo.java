/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * ConcurrentDemo
 *
 * @author liuzhen
 * @version 1.0.0 2022/8/29 14:35
 */
public class ConcurrentDemo {

    private static List<Team> teamList = new ArrayList<>();
    // private static List<Team> teamList = new CopyOnWriteArrayList<>();

    private static Object object = new Object();

    public static List<Team> getTeamList() {
        synchronized (object) {
            return teamList;
        }
    }

    public static void main(String[] args) {
        teamList.add(new Team(1, "zhangsan", 100));
        teamList.add(new Team(2, "刘亦菲", 99));
        teamList.add(new Team(3, "姚明", 100));

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                for (Team team : getTeamList()) {
                    System.out.println(team.toString());
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (object) {
                teamList.add(new Team(4, "4", 4));
            }
        }).start();
    }

    static class Team {
        public int playerId;
        public String playerName;
        public int lv;

        public Team() {
        }

        public Team(int playerId, String playerName, int lv) {
            this.playerId = playerId;
            this.playerName = playerName;
            this.lv = lv;
        }

        @Override
        public String toString() {
            return "Team{" + "playerId=" + playerId + ", playerName='" + playerName + '\'' + ", lv=" + lv + '}';
        }
    }

}
