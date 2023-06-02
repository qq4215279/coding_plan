package com.mumu.user;

public class StringDemo {

    public static void main(String[] args) {

        String s1 = "abcdefg";
        StringBuffer sb = new StringBuffer(s1);
        System.out.println(sb.toString());

        String s2 = "aaa?#bbb#ccc#ddd";
        int index = s2.indexOf("?");//3
        System.out.println(index);
        s2 = s2.substring(s2.indexOf("?"));
        System.out.println(s2);

        String s4 = "aaa?#bbb#ccc#ddd";
        s4 = s4.substring(0, s4.indexOf("?"));//aaa
        System.out.println("s4: " + s4);

        String[] lessonName = new String[] {"语文", "数学", "英语"};
        System.out.println("---------------->" + lessonName.toString());

    }
}
