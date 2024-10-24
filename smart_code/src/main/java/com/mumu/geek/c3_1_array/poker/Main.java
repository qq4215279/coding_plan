/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.geek.c3_1_array.poker;

import java.util.LinkedList;
import java.util.List;

/**
 * Main
 * @author liuzhen
 * @version 1.0.0 2024/8/19 15:50
 */
public class Main {
  public static void main(String[] args) {
    List<Poker> pokers = new LinkedList<>();

    for (PokerLevelEnum levelEnum : PokerLevelEnum.values()) {
      for (PokerTypeEnum typeEnum : PokerTypeEnum.values()) {
        if (typeEnum == PokerTypeEnum.BLANK) {
          continue;
        }
        if (levelEnum == PokerLevelEnum.LEVEL_S_X || levelEnum == PokerLevelEnum.LEVEL_L_X) {
          continue;
        }

        pokers.add(new Poker(typeEnum, levelEnum));
      }
    }

    pokers.add(new Poker(PokerTypeEnum.BLANK, PokerLevelEnum.LEVEL_S_X));
    pokers.add(new Poker(PokerTypeEnum.BLANK, PokerLevelEnum.LEVEL_L_X));

    for (Poker poker : pokers) {
      System.out.println(poker.toString() + "=" + poker.getId());
    }
  }
}
