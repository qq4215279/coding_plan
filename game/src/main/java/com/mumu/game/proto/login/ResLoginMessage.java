package com.mumu.game.proto.login;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.mumu.game.proto.basegame.GameInfoBean;
import com.google.common.collect.Lists;
import java.util.List;
import lombok.Data;

/** 登陆请求返回 @Date: 2024/7/31 上午10:27 @Author: xu.hai */
@Data
@ProtobufClass
public class ResLoginMessage {

  /** 玩家id */
  private PlayerBaseInfoBean playerBaseInfoBean;

  /** 系统时间 */
  private Long systemTime;

  /** 游戏信息 */
  private List<GameInfoBean> games = Lists.newArrayList();
}
