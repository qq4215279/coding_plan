package com.mumu.jdk_api.util;

import java.util.Arrays;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * InflaterTest
 * @author liuzhen
 * @version 1.0.0 2024/10/24 11:52
 */
public class InflaterTest {

  /**
   * 在 Java 中，压缩字节数组通常可以使用 java.util.zip 包中的 Deflater 和 Inflater 类。Deflater 用于压缩数据，而 Inflater 用于解压缩数据。
   * @date 2024/10/24 11:52
   */
  public static void main(String[] args) {
    // 示例数据
    String originalString = "Hello, this is a sample string to demonstrate byte array compression!";
    byte[] input = originalString.getBytes();

    // 压缩字节数组
    byte[] compressedData = compress(input);
    System.out.println("原始字节数组长度: " + input.length);
    System.out.println("压缩后字节数组长度: " + compressedData.length);

    // 解压缩字节数组
    byte[] decompressedData = decompress(compressedData);
    String outputString = new String(decompressedData);
    System.out.println("解压缩后的字符串: " + outputString);
  }

  public static byte[] compress(byte[] data) {
    Deflater deflater = new Deflater();
    // 将输入数据设置为要压缩的数据，并调用 finish() 方法。
    deflater.setInput(data);
    deflater.finish();

    // 创建一个字节数组来接收压缩后的数据
    byte[] buffer = new byte[1024];
    // 使用一个缓冲区来存储压缩后的数据，并调用 deflate() 方法来执行压缩。
    int compressedDataLength = deflater.deflate(buffer);
    deflater.end();

    // 返回压缩后的字节数组
    return Arrays.copyOf(buffer, compressedDataLength);
  }

  public static byte[] decompress(byte[] compressedData) {
    Inflater inflater = new Inflater();
    // 将压缩数据设置为要解压缩的数据。
    inflater.setInput(compressedData);

    // 创建一个字节数组来接收解压缩后的数据
    byte[] buffer = new byte[1024];
    int resultLength = 0;

    // 调用 inflate() 方法来执行解压缩。
    try {
      resultLength = inflater.inflate(buffer);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      inflater.end();
    }

    // 返回解压缩后的字节数组
    return Arrays.copyOf(buffer, resultLength);
  }

}
