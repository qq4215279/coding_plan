/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.jdk_api.util;

import org.junit.Test;

import java.util.Base64;

/**
 * Base64Test
 *
 * @author liuzhen
 * @version 1.0.0 2021/7/13 17:58
 */
public class Base64Test {

    /**
     * Base64 编码说明
     * Base64 是一种基于 64 个可打印字符来表示二进制数据的表示方法，由于 2^6=64，所以每 6 个比特为一个单元，对应某个可打印字符。
     * Base64 常用于在通常处理文本数据的场合，表示、传输、存储一些二进制数据，包括 MIME 的电子邮件及 XML 的一些复杂数据。
     * Base64 编码要求把 3 个 8 位字节（3*8=24）转化为 4 个 6 位的字节（4*6=24），之后在 6 位的前面补两个 0，形成 8 位一个字节的形式。 如果剩下的字符不足 3 个字节，则用 0 填充，输出字符使用 =，因此编码后输出的文本末尾可能会出现 1 或 2 个 =。
     *
     * 为了保证所输出的编码位可读字符，Base64 制定了一个编码表，以便进行统一转换。编码表的大小为 2^6=64，这也是 Base64 名称的由来。
     * 在 Base64 中的可打印字符包括字母 A-Z、a-z、数字 0-9，这样共有 62 个字符，此外两个可打印符号在不同的系统中而不同。
     * 以下是 Base64 编码的基本步骤：
     * 将数据划分为 3 个字节一组（24位）。
     * 将每个字节转换为 8 位二进制形式。
     * 将 24 位数据按照 6 位一组进行划分，得到 4 个 6 位的组。
     * 将每个 6 位的组转换为对应的 Base64 字符。
     * 如果数据不足 3 字节，进行填充。
     * 将所有转换后的 Base64 字符连接起来，形成最终的编码结果。
     * 解码 Base64 编码的过程与编码相反，将每个 Base64 字符转换为对应的6位二进制值，然后将这些 6 位值组合成原始的二进制数据。
     *
     * Base64 编码具有以下特点：
     * 编码后的数据长度总是比原始数据长约 1/3。
     * 编码后的数据可以包含 A-Z、a-z、0-9 和两个额外字符的任意组合。
     * Base64 编码是一种可逆的编码方式，可以通过解码还原原始数据。
     */

    /**
     *
     * java.util.Base64类的使用场景包括但不限于以下几个方面：
     * 数据传输和存储：Base64编码可以将二进制数据转换为可打印的ASCII字符，使得数据在传输或存储时更易于处理。例如，通过网络传输二进制数据时，可以将其编码为Base64字符串，然后在接收端进行解码。
     * 数据加密：虽然Base64编码并不是一种加密算法，但它可以用于隐藏敏感数据的原始格式。在某些情况下，使用Base64编码可以防止未经授权的人员直接读取数据内容。
     * URL和文件传输：Base64编码还可以在URL传输中使用，因为有些字符在URL中具有特殊含义，可能导致传输错误。通过对数据进行Base64编码，可以避免这些问题。此外，Base64编码也可以用于将二进制文件转换为纯文本格式进行传输。
     * 数据序列化：在某些情况下，需要将复杂的数据结构进行序列化并存储或传输。通过将数据进行Base64编码，可以将其转换为字符串形式，便于保存到文本文件、数据库或在不同平台之间传输。
     * 需要注意的是，Base64编码会导致编码后的数据长度增加约33%。因此，在对大量数据进行Base64编码时，需要考虑数据大小和传输效率。此外，Base64编码是一种可逆操作，但并不提供数据加密的安全性。如果需要对数据进行加密保护，应该使用加密算法而不是Base64编码。
     *
     * 综上所述，java.util.Base64类的使用场景包括数据传输和存储、数据加密、URL和文件传输以及数据序列化等。根据具体的需求和场景，您可以选择适当的Base64编码方式和方法。
     */

    @Test
    public void encoder() {
        // Encoder
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] bytes = "123456hh你好".getBytes();
        System.out.println(bytes);
        System.out.println(bytes.toString());

        byte[] encode = encoder.encode(bytes);
        System.out.println("encode: " + new String(encode));

        // Decoder
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(encode);
        System.out.println("decode: " + new String(decode));

    }

}
