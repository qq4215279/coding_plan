package com.mumu.jdk_api.javaCompiler;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * JavaCompileDemo
 *
 * @author liuzhen
 * @version 1.0.0 2024/5/23 15:59
 */
public class JavaCompileDemo {
    public static void main(String[] args) {
        // Java源文件的路径
        String sourceFilePath = "F:\\Code\\MumuSpace\\coding_plan\\tech_enhance\\src\\main\\java\\com\\mumu\\jdk_api\\javaCompiler\\HelloWorld.java";

        // 获取系统Java编译器
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        // 检查编译器是否可用
        if (compiler == null) {
            System.err.println("Java Compiler is not available. Make sure you are using a JDK (not a JRE).");
            return;
        }

        // 编译Java源文件
        int result = compiler.run(null, null, null, sourceFilePath);

        // 检查编译结果
        if (result == 0) {
            System.out.println("Compilation successful.");
        } else {
            System.out.println("Compilation failed.");
        }
    }
}
