package com.mumu.dp1_4_prototype.deepclone;

public class Client {
    public static void main(String args[]) {
        WeeklyLog logPrevious, logNew = null;
        // 创建原型对象
        logPrevious = new WeeklyLog();
        // 创建附件对象
        Attachment attachment = new Attachment();
        // 将附件添加到周报中
        logPrevious.setAttachment(attachment);
        try {
            // 调用深克隆方法创建克隆对象
            logNew = logPrevious.deepClone();
        } catch (Exception e) {
            System.err.println("克隆失败！");
        }

        // 比较周报
        System.out.println("周报是否相同？ " + (logPrevious == logNew)); // false
        // 比较附件
        System.out.println("附件是否相同？ " + (logPrevious.getAttachment() == logNew.getAttachment())); // false
    }
}
