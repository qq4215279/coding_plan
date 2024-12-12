/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.common.redis;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommonException extends RuntimeException {
	private static final long serialVersionUID = 4737362918831649913L;

	private static Map<Integer, ErrorStruct> map = new HashMap<Integer, ErrorStruct>();

	private String message;

	private int code;

	private long uid;

	public CommonException(String message) {
		super(message);
		this.message = message;
	}

	public CommonException(int code) {
		super(String.valueOf(code));
		this.code = code;
		this.message = getErrorMsgByCode(code);
	}

	public CommonException(long uid, int code) {
		super(String.valueOf(code));
		this.code = code;
		this.uid=uid;
		this.message = getErrorMsgByCode(code);
	}
	
	public CommonException(int code, String message) {
		super(Integer.toString(code));
		this.message = message;
		this.code = code;
	}

	public CommonException(long uid, int code, String message) {
		super(Integer.toString(code));
		this.uid=uid;
		this.message = message;
		this.code = code;
	}

	public CommonException(Throwable t) {
		super(t);
	}

	public CommonException(int errorCode, Exception e) {
		super(Integer.toString(errorCode));
		this.code = errorCode;
		this.message = e.getMessage();
		this.message = this.message == null ? "NONE" : this.message;
		this.setStackTrace(e.getStackTrace());
	}

	public CommonException(int errorCode, Throwable e) {
		super(Integer.toString(errorCode));
		this.code = errorCode;
		this.message = e.getMessage();
		this.message = this.message == null ? "NONE" : this.message;
		this.setStackTrace(e.getStackTrace());
	}

	public CommonException(long uid, int errorCode, Throwable e) {
		super(Integer.toString(errorCode));
		this.uid=uid;
		this.code = errorCode;
		this.message = e.getMessage();
		this.message = this.message == null ? "NONE" : this.message;
		this.setStackTrace(e.getStackTrace());
	}

	public CommonException(int errorCode, Throwable e, String errorDetail) {
		super(Integer.toString(errorCode));
		this.code = errorCode;
		this.message = "Code:" + errorCode + "Msg:" + e.getMessage() + "Cause:" + e.getCause() + " Detail:"
				+ errorDetail;
		this.setStackTrace(e.getStackTrace());
	}

	/**
	 * 为了向客户端推送错误消息时能够发送ErrorMsg
	 * 所以platform-server，player-server等serviceServer不需要init
	 */
	// public static void initErrorCode() {
	// 	try (InputStream istream = com.cxx.hf.exception.CommonException.class
	// 			.getResourceAsStream("/com/cxx/hf/exception/errorcode.json")) {
	// 		if (istream != null) {
	// 			GsonBuilder builder = new GsonBuilder();
	// 			Gson gson = builder.create();
	// 			Scanner scanner = null;
	// 			StringBuilder buffer = new StringBuilder();
	// 			try {
	// 				scanner = new Scanner(istream, "utf-8");
	// 				while (scanner.hasNextLine()) {
	// 					buffer.append(scanner.nextLine());
	// 				}
	// 			} finally {
	// 				if (scanner != null) {
	// 					scanner.close();
	// 				}
	// 			}
	// 			String result = buffer.toString().replace("@", "");
	// 			result = result.replace(" ", "");
	// 			List<ErrorStruct> list = gson.fromJson(result, new TypeToken<List<ErrorStruct>>() {
	// 			}.getType());
	// 			for (ErrorStruct es : list) {
	// 				map.put(es.getId(), es);
	// 			}
	// 		}
	// 	} catch (IOException e) {
	// 		e.printStackTrace();
	// 	}
	//
	// }

	public static String getErrorMsgByCode(int code) {
		if (!map.containsKey(code)) {
			return "";
		}
		return map.get(code).getComment();
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		// initErrorCode();
		System.out.println(getErrorMsgByCode(-2));
	}
}
