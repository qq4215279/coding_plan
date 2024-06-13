package com.mumu.design.timer.redis;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.lang.reflect.Type;

/**
 * @ClassName GsonUtil
 * @Description Gson工具类
 * @author cxx-cz
 * @Date 2017年6月21日 下午5:21:15
 * @version 1.0.0
 */
@Data
@Getter(AccessLevel.PROTECTED)
public class GsonUtil {

	/**
	 * 作用是用于,如果用@Expose注解,表示此字段需要json.
	 */
	// protected static final GsonBuilder builder = new GsonBuilder()
	// .excludeFieldsWithoutExposeAnnotation()
	// .registerTypeAdapter(Timestamp.class, new TimestampTypeAdapter())
	// .registerTypeAdapter(Long.class, new LongTypeAdapter());

	protected static final GsonBuilder builder = new GsonBuilder();

	protected static final Gson gson = builder.create();

	protected static final Gson gsonDisableHtml = builder.disableHtmlEscaping().create();

	private static final String DEFAULT_DATE_PATTERN = "yyyyMMddHHmmss";


	private static Gson defaultGson;

	static {
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat(DEFAULT_DATE_PATTERN);
		defaultGson = builder.create();
		defaultGson.toJson(null);
	}

	/**
	 * 将字符型转化为JSON对象
	 * 
	 * @param obj
	 * @return
	 */
	public static JsonObject toJsonObject(String para) {
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = parser.parse(para).getAsJsonObject();
		return jsonObject;
	}

	/**
	 * 将对象转化为JSON字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}


	public static String toJsonDisableHtml(Object obj) {
		return gsonDisableHtml.toJson(obj);
	}

	/**
	 * 将JSON字符串转化为对象
	 * 
	 * @param obj
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> clazz) {
		return gson.fromJson(json, clazz);
	}

	public static <T> T fromJsonDisableHtml(String json, Class<T> clazz) {
		return gsonDisableHtml.fromJson(json, clazz);
	}

	/**
	 * 将JSON字符串转化为对象
	 * 
	 * @param obj
	 * @return
	 */
	public static <T> T fromJson(String json, Type type) {
		if (json == null || json.isEmpty()) {
			json = "{}";
		}
		return gson.fromJson(json, type);
	}

	/**
	 * 将Json对象中指定字段转化为对象
	 * 
	 * @param obj
	 * @return
	 */
	public static <T> Object fromJson(JsonObject json, String key, Class<T> clazz) {
		JsonElement el_ec = json.get(key);
		Object value = gson.fromJson(el_ec, clazz);
		return value;
	}


	public static <T> T fromJson(String json, com.google.common.reflect.TypeToken<T> token) {
		return fromJson(json, token, null);
	}

	public static <T> T fromJson(String json, TypeToken<T> token, String datePattern) {
		return fromJson(json, token.getType(), datePattern);
	}

	public static <T> T fromJson(String json, Type type, String datePattern) {
		if (null == json || "".equals(json)) {
			return null;
		}

		if (null == datePattern || "".equals(datePattern)) {
			try {
				return defaultGson.fromJson(json, type);
			} catch (Exception ignore) {
			}
			return null;
		}

		try {
			Gson gson = getGson(datePattern);
			return gson.fromJson(json, type);
		} catch (Exception ignore) {
		}
		return null;
	}

	private static Gson getGson(String datePattern) {
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat(datePattern);
		return builder.create();
	}

	/**
	 * 格式化输出JSON字符串
	 *
	 * @return 格式化后的JSON字符串
	 */
	public static String toPrettyFormat(String json) {
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(jsonObject);
	}


}
