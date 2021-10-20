package com.mumu.dp1_3_builder.core;

public class Actor {
    // 角色类型
    private String type;
    // 性别
    private String sex;
    // 脸型
    private String face;
    // 服装
    private String costume;
    // 发型
    private String hairstyle;

    public void setType(String type) {
        this.type = type;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public void setCostume(String costume) {
        this.costume = costume;
    }

    public void setHairstyle(String hairstyle) {
        this.hairstyle = hairstyle;
    }

    public String getType() {
        return (this.type);
    }

    public String getSex() {
        return (this.sex);
    }

    public String getFace() {
        return (this.face);
    }

    public String getCostume() {
        return (this.costume);
    }

    public String getHairstyle() {
        return (this.hairstyle);
    }
}
