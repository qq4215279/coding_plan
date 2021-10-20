package com.mumu.dp1_4_prototype.shallowclone;

public class Client {
    public static void main(String args[]) {
        WeeklyLog logPrevious, logNew1, logNew2;
        // 创建原型对象
        logPrevious = new WeeklyLog();
        // 创建附件对象
        Attachment attachment = new Attachment();
        // 将附件添加到周报中
        logPrevious.setAttachment(attachment);

        // 调用克隆方法创建克隆对象
        logNew1 = logPrevious.clone();
        // 比较周报
        System.out.println("周报是否相同？ " + (logPrevious == logNew1)); // false
        // 比较附件
        System.out.println("附件是否相同？ " + (logPrevious.getAttachment() == logNew1.getAttachment())); // true

        // 调用克隆方法创建克隆对象
        logNew2 = logPrevious.clone2();
        // 比较周报
        System.out.println("周报是否相同？ " + (logPrevious == logNew2)); // false
        // 比较附件
        System.out.println("附件是否相同？ " + (logPrevious.getAttachment() == logNew2.getAttachment())); // true
    }
}
