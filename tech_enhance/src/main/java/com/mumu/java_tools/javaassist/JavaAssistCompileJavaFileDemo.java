package com.mumu.java_tools.javaassist;

import javassist.ClassPool;
import javassist.CtClass;

/**
 * JavaAssistCompileJavaFileDemo
 *
 * @author liuzhen
 * @version 1.0.0 2024/5/23 16:03
 */
public class JavaAssistCompileJavaFileDemo {
    public static void main(String[] args) {
        try {
            // 创建类池
            ClassPool pool = ClassPool.getDefault();

            // 加载现有的类
            // CtClass cc = pool.get("com.mumu.java_tools.javaassist.JavaAssistDemo");
            CtClass cc = pool.get("com.mumu.jdk_api.javaCompiler.HelloWorld");

            // 进行你需要的修改

            // 将修改后的类写到文件中
            cc.writeFile(JavaAssistDemo.CLASSES_OUT_DIR); // 这里的"output"是输出目录

            System.out.println("Class file modified and saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
