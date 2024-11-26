/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.common;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;


/**
 * ErrorCode
 * 错误码
 * @author Auto-generated
 * @version 1.0.0 2024/9/9 10:00
 */
@ProtobufClass
public enum ErrorCode {
  /** 成功 */
  SUCCESS,
  /** 失败 */
  FAIL,
  /** 非法请求 */
  ILLEGAL,
  /** 未知错误 */
  UNKNOWN,
  /** 超时 */
  TIME_OUT,
  /** 连接异常 */
  SOCKET_ERROR,
  /** 连接关闭 */
  SOCKET_CLOSED,
  /** token 认证失败 */
  FAIL_TOKEN,
  /** 玩家不在线 */
  FAIL_PLAYER_OFFLINE,
  /** 未知玩家（玩家不存在） */
  FAIL_PLAYER_UNKNOWN,
  /** 未知桌子（桌子不存在） */
  FAIL_TABLE_UNKNOWN,
  /** 进入游戏失败 */
  FAIL_ENTER_GAME,
  /** 游戏不存在 */
  FAIL_GAME_UNKNOWN,
  /** 玩家正在游戏中 */
  FAIL_PLAYER_IN_GAME,
  /** 玩家正在匹配中 */
  FAIL_MATCHING,
  /** 玩家未在匹配中 */
  FAIL_UNMATCH,
  /** 匹配失败 */
  FAIL_MATCH_ERROR,
  /** 匹配超时 */
  FAIL_MATCH_TIMEOUT,
  /** 玩家不存在 */
  FAIL_PLAYER_NOT_EXIST,
  /** 玩家等待操作 */
  FAIL_PLAYER_NO_BIDER,
  /** 指令不存在 */
  FAIL_COMMAND_NOT_EXIST,
  /** 参数错误 */
  FAIL_PARAM_ERROR,
  /** 参数为空 */
  FAIL_PARAM_EMPTY,
  /** 命令请求超时 */
  FAIL_REQUEST_COMMAND_TIMEOUT,
  /** 无法操作当前指令 */
  FAIL_CAN_NOT_EXE_COMMAND,
  /** 已确认主花色 */
  FAIL_CONFIRM_MAIN_COLOR,
  /** 当前牌不符合出牌规则 */
  FAIL_UN_CONFORM_RULE_POKER,
  /** 重新登陆 */
  FAIL_RE_LOGIN,
}