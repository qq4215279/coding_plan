package com.mumu.game.proto.basegame;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public enum PlayerTableAction implements EnumReadable {
  ENTER(0), EXIT(1);
  private final int value;

  PlayerTableAction(int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }
}
