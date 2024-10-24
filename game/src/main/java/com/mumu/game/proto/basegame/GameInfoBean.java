package com.mumu.game.proto.basegame;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import java.util.List;
import lombok.Data;

/** 游戏信息 @Date: 2024/8/26 上午10:59 @Author: xu.hai */
@Data
@ProtobufClass
public class GameInfoBean {
  /** 游戏id */
  private int gameId;

  /** 游戏名称 */
  private String gameName;

  /** 房间ID列表 */
  private List<Integer> roomIds;

  /** 游戏大版本 */
  private int major;

  /** 游戏小版本 */
  private int minor;

  /** 游戏开始时间 */
  private long startTime;
}
