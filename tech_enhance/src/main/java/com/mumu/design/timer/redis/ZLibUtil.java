package com.mumu.design.timer.redis;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/**
 * @ClassName ZLibUtil
 * @Description ZLib解压缩工具类
 * @author cxx-cz
 * @Date 2017年6月21日 下午5:53:53
 * @version 1.0.0
 */
@Slf4j
public abstract class ZLibUtil {
	/**
	 * 压缩
	 * 
	 * @param data
	 *            待压缩数据
	 * @return byte[] 压缩后的数据
	 */
	public static byte[] compress(byte[] data) {
		if (data.length <= 0) {
			return data;
		}
		byte[] output = new byte[0];

		Deflater compresser = new Deflater();
		try {
			compresser.reset();
			compresser.setInput(data);
			compresser.finish();

			try (ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);) {
				byte[] buf = new byte[1024];
				while (!compresser.finished()) {
					int i = compresser.deflate(buf);
					bos.write(buf, 0, i);
				}
				output = bos.toByteArray();
			}
		} catch (Exception e) {
			log.error(Utility.getTraceString(e));
		} finally {
			compresser.reset();
			// BasePooledObjectFactory.getInstance().returnObject(compresser);
		}
		return output;
	}

	/**
	 * 压缩
	 * 
	 * @param data
	 *            待压缩数据
	 * 
	 * @param os
	 *            输出流
	 */
	public static void compress(byte[] data, OutputStream os) {
		DeflaterOutputStream dos = new DeflaterOutputStream(os);

		try {
			dos.write(data, 0, data.length);

			dos.finish();

			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解压缩
	 * 
	 * @param data
	 *            待压缩的数据
	 * @return byte[] 解压缩后的数据
	 */
	public static byte[] decompress(byte[] data) {
		if (data.length <= 0) {
			return data;
		}
		byte[] output = new byte[0];

		// Inflater decompresser = (Inflater) BasePooledObjectFactory.getInstance().borrowObject(Inflater.class);
		Inflater decompresser = new Inflater();
		try {
			decompresser.reset();
			decompresser.setInput(data);
			try (ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);) {
				byte[] buf = new byte[1024];
				while (!decompresser.finished()) {
					int i = decompresser.inflate(buf);
					o.write(buf, 0, i);
				}
				output = o.toByteArray();
			}
		} catch (Exception e) {
			log.error(Utility.getTraceString(e));
		} finally {
			decompresser.reset();
			// BasePooledObjectFactory.getInstance().returnObject(decompresser);
		}
		return output;
	}

	/**
	 * 解压缩
	 * 
	 * @param is
	 *            输入流
	 * @return byte[] 解压缩后的数据
	 */
	public static byte[] decompress(InputStream is) {
		InflaterInputStream iis = new InflaterInputStream(is);
		ByteArrayOutputStream o = new ByteArrayOutputStream(1024);
		try {
			int i = 1024;
			byte[] buf = new byte[i];

			while ((i = iis.read(buf, 0, i)) > 0) {
				o.write(buf, 0, i);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return o.toByteArray();
	}

	public static void main(String[] args) {
		System.out.println("字节压缩／解压缩测试");
		String inputStr = "站搜啊的说得很好snowolf@zlex.org;dongliang@zlex.org;zlex.dongliang@zlex.org";
		System.out.println("输入字符串:" + inputStr);
		byte[] input = inputStr.getBytes();
		System.out.println("输入字节长度:" + input.length);

		byte[] data = ZLibUtil.compress(input);
		System.out.println("压缩后字节长度:" + data.length);

		byte[] output = ZLibUtil.decompress(data);
		System.out.println("解压缩后字节长度:" + output.length);
		String outputStr = new String(output);
		System.out.println("输出字符串:" + outputStr);
	}
}
