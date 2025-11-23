/*
 * Copyright 2020-2025, mumu without 996.
 * All Right Reserved.
 */

package com.mumu.game.demo37;

public class Player {
    /** 玩家id */
    private long playerId;
    /** 淘汰玩家数量 */
    private int outPlayerCount;
    /** 最终血量 */
    private long hp;
    /** 排名 */
    private int rank;
    
    public Player(long playerId, int outPlayerCount, long hp) {
        this.playerId = playerId;
        this.outPlayerCount = outPlayerCount;
        this.hp = hp;
    }

    
    // Getter 和 Setter 方法
    public long getPlayerId() {
        return playerId;
    }
    
    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }
    
    public int getOutPlayerCount() {
        return outPlayerCount;
    }
    
    public void setOutPlayerCount(int outPlayerCount) {
        this.outPlayerCount = outPlayerCount;
    }
    
    public long getHp() {
        return hp;
    }
    
    public void setHp(long hp) {
        this.hp = hp;
    }
    
    public int getRank() {
        return rank;
    }
    
    public void setRank(int rank) {
        this.rank = rank;
    }
    
    @Override
    public String toString() {
        return "Player{id=" + playerId + ", hp=" + hp + ", outCount=" + outPlayerCount + ", rank=" + rank + "}";
    }
}