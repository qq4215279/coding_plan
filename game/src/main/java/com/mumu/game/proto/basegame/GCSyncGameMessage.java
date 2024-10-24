package com.mumu.game.proto.basegame;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.mumu.game.proto.baloot.BalootTableBean;
import java.util.List;
import lombok.Data;

/** 客户端请求同步游戏信息返回 @Date: 2024/8/21 上午11:57 @Author: xu.hai */
@Data
@ProtobufClass
public class GCSyncGameMessage {

  /** 桌上玩家信息 */
  private List<GamePlayerBean> players;
  /** baloot桌子消息 */
  private BalootTableBean balootInfo;
}
