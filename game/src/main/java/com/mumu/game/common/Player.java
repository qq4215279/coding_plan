
package com.mumu.game.common;

import java.io.Serializable;
import lombok.Data;

/**
 * @author cxx-cz
 * @version 1.0.0
 * @ClassName Player
 * @Description 玩家信息
 * @Date 2017年7月4日 下午5:31:42
 */
@Data
public class Player implements Serializable {
	private static final long serialVersionUID = 7400436197166502202L;

	/**
	 * @Field playerId : 玩家ID
	 */
	protected long playerId;

	/**
	 * @Field accountId : 账户id
	 */
	private long accountId;

	/**
	 * @Field channel : 渠道
	 */
	private String channel;

	/**
	 * @Field cellNo : 手机号
	 */
	private String cellNo;

	/**
	 * @Field colorfulId : 炫彩id
	 */
	private long colorfulId;

	/** 是否实名认证新版 */
	private boolean certificationNew;

	/** 实名认证系统状态，-1：未认证，0：认证成功，1：认证中，2：认证失败 */
	private int certificationStatus = -1;

	/**
	 * @Field nick : 昵称
	 */
	private String nick;

	/**
	 * @Field head : 头像
	 */
	private String head;

	/**
	 * @Field level : 等级
	 */
	private short level;

	/**
	 * vip等级
	 */
	private short vip;

	/**
	 * @Field exp : 经验值(不停累加) 货币一律改为存放itemMap里
	 */
	@Deprecated
	private long exp;
	/**
	 * vip经验值(不停累加-与RMB是1:1比例) 货币一律改为存放itemMap里
	 */
	@Deprecated
	private long vipExp;
	/**
	 * @Field chargeCumulative : 累计充值
	 */
	private int chargeCumulative;

	/**
	 * 累计充值次数
	 */
	private int chargeNum;

	/**
	 * @Field banChatTime : 禁言截止时间
	 */
	private long banChatTime;

	/**
	 * @Field onlineTimeMS : 在线时长[ 大厅挂机的时间也算 ] ,毫秒
	 */
	private long onlineTimeMS;

	/**
	 * @Field isOnline : 玩家当前是否在线
	 */
	private boolean isOnline;

	/**
	 * @Field createTime : 创建时间
	 */
	protected long createTime;

	/**
	 * @Field lastLoginTime : 上次登录时间
	 */
	private long lastLoginTime;

	/**
	 * @Field lastLogoutTime : 上次登出时间
	 */
	private long lastLogoutTime;

	/**
	 * @Field lastLoginIp : 上次登录ip
	 */
	private String lastLoginIp;

	/**
	 * @Field lastLoginPort : 上次登录port
	 */
	private int lastLoginPort;

	/**
	 * @Field lastLoginType : 上次登录方式
	 */
	private int lastLoginType;

	/** 上次登录所在地区 */
	private String lastLoginRegion;

	/**
	 * @Field lastChargeTime : 上次充值时间
	 */
	private long lastChargeTime;

	/**
	 * @Field lastIMEI : 上次登陆的设备信息
	 */
	private String lastIMEI = "";

	/**
	 * @Field version : 客户端版本号
	 */
	private String version;

	/**
	 * @Field version : 服务端版本号(以最后一次登录为准)
	 */
	private String serverVersion;

	/**
	 * @Field toDayOnlineTimes : 当天在线时长(毫秒)
	 */
	private int todayOnlineMillisecond;

	/**
	 * 称号特权
	 */
	private int expPrivilege;

	/**
	 * 年龄
	 */
	private int age;

	public Player(long playerId) {
		super();
		this.playerId = playerId;
		this.colorfulId = 0;
		this.cellNo = "";
		this.certificationNew = false;
		this.nick = "XX";
		this.head = "";
		this.level = 1;
		this.exp = 0;
		this.vip = 0;
		this.vipExp = 0L;
		this.chargeCumulative = 0;
		this.banChatTime = 0L;
		this.onlineTimeMS = 0L;
		this.createTime = System.currentTimeMillis();
		this.lastLoginTime = System.currentTimeMillis();
		this.lastLoginIp = "";
		this.lastLoginPort = 0;
		this.lastChargeTime = 0L;
		this.version = "";
		this.serverVersion = "";
		this.age = 0;
		this.lastLoginType = 0;
	}

}