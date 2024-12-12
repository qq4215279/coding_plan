/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.common.redis;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * Utility
 *
 * @author liuzhen
 * @version 1.0.0 2024/6/6 10:18
 */
@Slf4j
public class Utility {
    public static void main(String[] args) {
        for(int i =3001;i<=4000;i++) {
            System.out.println(i+" "+ i % 174);
        }
//		for (int i = 1; i <= 1000; i++) {
//			String ret = getVerificationCode(6);
//			System.out.println(ret);
//			if (ret.length() > 6 || !Checker.isNumeric(ret)) {
//				System.out.println("________________");
//			}
//		}
//		String s = "HuaWei8n7g]ub&f%9[&$y3Q82g";
//		System.out.println(getMD5Low(s));
//		// String[] r = new String[] { "sh /opt/IW2/ulink_start.sh" };
//		// String[] s = ArrayUtils.EMPTY_STRING_ARRAY;
//		// s = (String[]) ArrayUtils.add(s, "/bin/sh");
//		// s = (String[]) ArrayUtils.add(s, "-c");
//		// String[] ret = (String[]) ArrayUtils.addAll(s, r);
//		// System.out.println(Arrays.toString(ret));
//		// String[] cmds = { "cmd", "/C", "start /b
//		// D:\\workspace\\xlsx2jsonAndXml\\export-jsonAndxml.bat" };
//		// exeCmd(cmds);
//		long[] playerSeat = { 1L, 2L, 3L, 4L };
//		System.out.println(binarySearch(playerSeat, 1));
//		System.out.println(binarySearch(playerSeat, 2));
//		System.out.println(binarySearch(playerSeat, 3));
//		System.out.println(binarySearch(playerSeat, 5));
    }

    public static String convert(String utfString) {
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;

        while ((i = utfString.indexOf("\\u", pos)) != -1) {
            sb.append(utfString.substring(pos, i));
            if (i + 5 < utfString.length()) {
                pos = i + 6;
                sb.append((char) Integer.parseInt(utfString.substring(i + 2, i + 6), 16));
            }
        }

        return sb.toString();
    }

    public static int binarySearch(int srcArray[], int key) {
        return binarySearch(srcArray, 0, srcArray.length - 1, key);
    }

    public static int binarySearch(int srcArray[], int start, int end, int key) {
        int mid = (end - start) / 2 + start;
        if (srcArray[mid] == key) {
            return mid;
        }
        if (start >= end) {
            return -1;
        } else if (key > srcArray[mid]) {
            return binarySearch(srcArray, mid + 1, end, key);
        } else if (key < srcArray[mid]) {
            return binarySearch(srcArray, start, mid - 1, key);
        }
        return -1;
    }

    public static int binarySearch(long srcArray[], long key) {
        return binarySearch(srcArray, 0, srcArray.length - 1, key);
    }

    public static int binarySearch(long srcArray[], int start, int end, long key) {
        int mid = (end - start) / 2 + start;
        if (srcArray[mid] == key) {
            return mid;
        }
        if (start >= end) {
            return -1;
        } else if (key > srcArray[mid]) {
            return binarySearch(srcArray, mid + 1, end, key);
        } else if (key < srcArray[mid]) {
            return binarySearch(srcArray, start, mid - 1, key);
        }
        return -1;
    }

    public static boolean isInt(Type type) {
        return type == int.class || type == Integer.class;
    }

    public static boolean isString(Type type) {
        return type == String.class;
    }

    public static boolean isLong(Type type) {
        return type == long.class || type == Long.class;
    }

    public static String exeBash(String[] bashs) throws Exception {
        String[] s = ArrayUtils.EMPTY_STRING_ARRAY;
        s = (String[]) ArrayUtils.add(s, "bash");
        // s = (String[]) ArrayUtils.add(s, "-c");
        String[] retBashs = (String[]) ArrayUtils.addAll(s, bashs);
        try {
            Process pro = Runtime.getRuntime().exec(retBashs);
            BufferedReader read = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line = null;
            log.info("exec bash: {}", Arrays.toString(bashs));
            while ((line = read.readLine()) != null) {
                result.append(line);
                result.append("\n");
                LoggerFactory.getLogger("action").info("{}", line);
            }
            // 读取标准错误流
            BufferedReader brError = new BufferedReader(new InputStreamReader(pro.getErrorStream()));
            StringBuilder errResult = new StringBuilder();
            String errline = null;
            while ((errline = brError.readLine()) != null) {
                errResult.append(errline);
                errResult.append("\n");
                LoggerFactory.getLogger("action").error("{}", errline);
            }

            int code = pro.waitFor();
            if (errResult.length()>2){
                String errLogStr = errResult.substring(errResult.indexOf("&&") + 2,errResult.lastIndexOf("\n"));
                // -- 截取node错误输出不展示
                String nodeErrTips = "(Use `node --trace-uncaught ...` to show where the exception was thrown)";
                if (errLogStr.contains(nodeErrTips)){
                    errLogStr = errLogStr.substring(0,errLogStr.indexOf(nodeErrTips));
                }
                log.info("exec bash={},result={},output={}", retBashs, code, errLogStr);
                throw new Exception(errLogStr);
            }
            log.info("exec bash={},result={},output={}", retBashs, code, result.toString());
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("exec bash={},error={}", retBashs, e.getMessage());
            throw e;
        }
    }

//     public static void exeBashCommand(String[] commands) {
//         try {
// //			Process pro = Runtime.getRuntime().exec(commands);
// //			pro.waitFor(10, TimeUnit.MINUTES);
// //			log.info("exec bash={}", GsonUtil.toJson(commands));
//
//             log.info("start exec bash={}", GsonUtil.toJson(commands));
//             Process process = Runtime.getRuntime().exec(commands);
//             new InputStreamRunnable(process.getErrorStream(), "Error").start();
//             new InputStreamRunnable(process.getInputStream(), "Info").start();
//             boolean code = process.waitFor(10, TimeUnit.MINUTES);
//             log.info("exec code={}", code);
//             process.destroy();
//             log.info("end exec");
//         } catch (Exception e) {
//             e.printStackTrace();
//             log.error("exec bash={},error={}", Arrays.toString(commands), e.getMessage());
//         }
//     }

    public static String exeCmd(String[] cmds) {
        InputStream in = null;
        try {
            Process pro = Runtime.getRuntime().exec(cmds);
            pro.waitFor();
            in = pro.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in));
            StringBuilder result = new StringBuilder();
            String line = null;
            while ((line = read.readLine()) != null) {
                result.append(line);
            }
            log.info("exec cmds={},result={}", cmds, result);
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("exec cmds={},error={}", cmds, e.getMessage());
        }
        return "0";
    }

    /***
     * true:already in using false:not using
     *
     * @param port
     */
    public static boolean isLocoalPortUsing(int port) {
        boolean flag = true;
        try {
            flag = isPortUsing("127.0.0.1", port);
        } catch (Exception e) {
        }
        return flag;
    }

    /***
     * true:already in using false:not using
     *
     * @param host
     * @param port
     * @throws UnknownHostException
     */
    public static boolean isPortUsing(String host, int port) throws UnknownHostException {
        boolean flag = false;
        InetAddress theAddress = InetAddress.getByName(host);
        try (Socket socket = new Socket(theAddress, port);) {
            flag = true;
        } catch (IOException e) {
            log.error("host={},post={} is not using!", host, port);
        }
        return flag;
    }

    public static String getStr4SQLINParam(Object[] objects) {
        List<Object> valueList = Arrays.asList(objects);
        return valueList.toString().replace("[", "(").replace("]", ")");
    }

    /** Trace保留的行数 **/
    private static final int TRACE_NUM = 32;

    public static String getTraceString(Throwable e) {
        if (e == null) {
            return "NULL";
        }
        // e.getCause()有值,取其内部的Throwable信息
        if (e.getCause() != null) {
            e = e.getCause();
        }
        StackTraceElement[] stackTrace = e.getStackTrace();
        StringBuilder sb = new StringBuilder(512);
        String msg = e.getMessage();
        msg = msg == null ? "NULL" : msg;
        sb.append("##Message:").append(msg).append(" ##\n");
        sb.append("##ThreadName:").append(Thread.currentThread().getName()).append(" ##\n");
        sb.append("##Exception:" + e.toString() + "##\n");
        int i = 1;
        for (StackTraceElement stack : stackTrace) {
            if (i++ > TRACE_NUM) {
                break;
            }
            sb.append(stack.toString() + "\n");
        }
        return sb.toString();
    }

    /**
     * 获取当前线程的堆栈信息
     * @param msg
     * @return
     */
    public static String getCurrentThreadTraceString(String msg) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StringBuilder sb = new StringBuilder(512);
        msg = msg == null ? "NULL" : msg;
        sb.append("##Message:").append(msg).append("##\n");
        int i = 1;
        for (StackTraceElement stack : stackTrace) {
            if (i++ > TRACE_NUM) {
                break;
            }
            sb.append(stack.toString()).append("\n");
        }
        return sb.toString();
    }

    public static String getTraceString(Throwable e, long uid) {
        if (e == null)
            return "NULL";

        // 处理反射报错
        if (e instanceof InvocationTargetException) {
            Throwable target = ((InvocationTargetException) e).getTargetException();
            if (target != null) {
                e = target;
            }
        }
        StackTraceElement[] stackTrace = e.getStackTrace();
        StringBuffer sb = new StringBuffer(512);
        String msg = e.getMessage();
        msg = msg == null ? "NULL" : msg;
        sb.append("##Message:" + msg + "##\n");
        sb.append("##Uid:" + uid + "##\n");
        sb.append("##Exception:" + e.toString() + "##\n");
        int i = 1;
        for (StackTraceElement stack : stackTrace) {
            if (i++ > TRACE_NUM)
                break;
            sb.append(stack.toString() + "\n");
        }
        return sb.toString();
    }

    static class ConditionTest {

        public static void main(String[] args) throws InterruptedException {
            Lock lock = new ReentrantLock();
            Condition condition = lock.newCondition();
            new Thread(() -> {
                lock.lock();
                try {
                    System.out.println(new Date() + "\tThread 1 is waiting");
                    try {
                        long waitTime = condition.awaitNanos(TimeUnit.SECONDS.toNanos(2));
                        System.out.println(new Date() + "\tThread 1 remaining time " + waitTime);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    System.out.println(new Date() + "\tThread 1 is waken up");
                } finally {
                    lock.unlock();
                }
            }).start();

            new Thread(() -> {
                lock.lock();
                try {
                    System.out.println(new Date() + "\tThread 2 is running");
                    try {
                        Thread.sleep(4000);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    condition.signal();
                    System.out.println(new Date() + "\tThread 2 ended");
                } finally {
                    lock.unlock();
                }
            }).start();
        }
    }

    // public static String getProtocolBufferString(GeneratedMessage msg) {
    // return msg != null ? replaceBlank(msg.toString()) : "";
    // }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * MD5 加密-大写
     */
    public static String getMD5Up(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * MD5 加密-小写
     */
    public static String getMD5Low(String str) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();

            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer(32);

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }

        return md5StrBuff.toString();
    }

    public static String getMD5Low(byte[] bytes) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();

            messageDigest.update(bytes);
        } catch (NoSuchAlgorithmException e) {
            log.error(getTraceString(e));
        }
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer(32);
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();
    }

    public static String getLocalIp() {
        InetAddress ia;
        try {
            ia = InetAddress.getLocalHost();
            return ia.getHostAddress();
        } catch (UnknownHostException e) {
            log.error(getTraceString(e));
        }
        return "";
    }

    // public static String getVerificationCode(int count) {
    //     StringBuilder value = new StringBuilder(count);
    //     for (int i = 0; i < count; i++) {
    //         value.append(RandomUtil.nextInt(1, 9));
    //     }
    //     return value.toString();
    // }

    public static boolean CheckRunInIDEA() {
        try {
            Class.forName("com.intellij.rt.execution.application.AppMainV2");
            return true;
        } catch (ClassNotFoundException ignored) {
            return false;
        }
    }
}
