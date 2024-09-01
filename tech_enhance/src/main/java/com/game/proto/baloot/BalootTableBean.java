package com.game.proto.baloot;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import java.util.List;
import lombok.Data;

@ProtobufClass
@Data
public class BalootTableBean {
  /** 状态: 1: 等待; 2: 发牌; 3: 叫牌; 4: 行牌; 5: 行牌结算; 6: 最终结算; */
  @Protobuf(fieldType = FieldType.INT32, order = 1, required = false)
  public Integer state;

  /**
   * 游戏指令: 0: 初始化; 1: 玩家准备; 2: 开始游戏，首次发牌; 3: 2次发牌; 4: 玩家叫牌; 5: 发底牌; 6: 发展示牌给指定玩家; 7: 游戏流局; 8: 玩家行牌;
   * 9: 行牌结算; 10: 最终结算;
   */
  @Protobuf(fieldType = FieldType.INT32, order = 2, required = false)
  public Integer action;

  /** 游戏模式: 0: 未确定; 1: hokum; 2: 2rd hokum; 3: sun; 4: ashkal */
  @Protobuf(fieldType = FieldType.INT32, order = 3, required = false)
  public Integer mode;

  /** 游戏倍数: 1: 默认1倍; 2: 2倍; 3: 3倍; 4: 4倍; 5: 5倍(gahwa); */
  @Protobuf(fieldType = FieldType.INT32, order = 4, required = false)
  public Integer multi;

  /** 盘数 */
  @Protobuf(fieldType = FieldType.INT32, order = 5, required = false)
  public Integer circle;

  /** 墩数 */
  @Protobuf(fieldType = FieldType.INT32, order = 6, required = false)
  public Integer round;

  /** 发牌员 */
  @Protobuf(fieldType = FieldType.INT32, order = 7, required = false)
  public Integer dealerSeatId;

  /** 主宰者 */
  @Protobuf(fieldType = FieldType.INT32, order = 8, required = false)
  public Integer masterSeatId;

  /** 加倍者 */
  @Protobuf(fieldType = FieldType.INT32, order = 9, required = false)
  public Integer multiSeatId;

  /** 展示牌 */
  @Protobuf(fieldType = FieldType.INT32, order = 10, required = false)
  public Integer displayPokerId;

  /** 展示牌 */
  @Protobuf(fieldType = FieldType.INT32, order = 11, required = false)
  public Integer displaySeatId;

  /** 当前叫牌玩家 */
  @Protobuf(fieldType = FieldType.INT32, order = 12, required = false)
  public Integer bidSeatId;

  /** 玩家命令集 */
  @Protobuf(fieldType = FieldType.OBJECT, order = 13, required = false)
  public List<CmdBean> cmdBeans;

  /** 操作倒计时 */
  @Protobuf(fieldType = FieldType.INT64, order = 14, required = false)
  public Long countdown;

  /** 队伍信息列表 */
  @Protobuf(fieldType = FieldType.OBJECT, order = 15)
  public List<TeamBean> teanbeans;

  /** 游戏主花色 */
  @Protobuf(fieldType = FieldType.INT32, order = 16, required = false)
  public Integer mainColorType;
}
