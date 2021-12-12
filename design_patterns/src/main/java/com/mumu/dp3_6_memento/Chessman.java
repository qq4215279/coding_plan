/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp3_6_memento;

// 象棋棋子类：原发器
public class Chessman {
    private String label;
    private int x;
    private int y;

    public Chessman(String label, int x, int y) {
        this.label = label;
        this.x = x;
        this.y = y;
    }

    // 保存状态
    public ChessmanMemento save() {
        return new ChessmanMemento(this.label, this.x, this.y);
    }

    // 恢复状态
    public void restore(ChessmanMemento memento) {
        this.label = memento.getLabel();
        this.x = memento.getX();
        this.y = memento.getY();
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getLabel() {
        return (this.label);
    }

    public int getX() {
        return (this.x);
    }

    public int getY() {
        return (this.y);
    }
}
