// package com.mumu.java_base.collection.list;
//
// import cn.hutool.core.lang.Pair;
// import com.mumu.design.baloot.common.constant.BalootConstans;
// import com.mumu.design.baloot.common.core.Poker;
// import com.mumu.design.baloot.common.enums.PokerLevelEnum;
// import com.mumu.design.baloot.common.enums.PokerTypeEnum;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.LinkedList;
// import java.util.List;
// import java.util.Map;
// import java.util.Queue;
//
// /**
//  * SameObListTest
//  * @author liuzhen
//  * @version 1.0.0 2024/12/18 15:19
//  */
// public class SameObListTest {
//   /** 手牌模板 */
//   static volatile Map<Integer, Pair<List<LinkedList<Poker>>, Integer>> pokerListTempMap = new HashMap<>();
//
//   static {
//     List<LinkedList<Poker>> list1 = new ArrayList<>();
//     LinkedList<Poker> link1List = new LinkedList<>();
//     link1List.offer(BalootConstans.getPoker(PokerTypeEnum.HEART, PokerLevelEnum.LEVEL_7));
//     link1List.offer(BalootConstans.getPoker(PokerTypeEnum.HEART, PokerLevelEnum.LEVEL_8));
//     link1List.offer(BalootConstans.getPoker(PokerTypeEnum.HEART, PokerLevelEnum.LEVEL_9));
//     link1List.offer(BalootConstans.getPoker(PokerTypeEnum.HEART, PokerLevelEnum.LEVEL_10));
//     list1.add(link1List);
//
//     LinkedList<Poker> link2List = new LinkedList<>();
//     link2List.offer(BalootConstans.getPoker(PokerTypeEnum.HEART, PokerLevelEnum.LEVEL_J));
//     link2List.offer(BalootConstans.getPoker(PokerTypeEnum.HEART, PokerLevelEnum.LEVEL_Q));
//     link2List.offer(BalootConstans.getPoker(PokerTypeEnum.HEART, PokerLevelEnum.LEVEL_K));
//     link2List.offer(BalootConstans.getPoker(PokerTypeEnum.HEART, PokerLevelEnum.LEVEL_A));
//     list1.add(link2List);
//
//     pokerListTempMap.put(1, Pair.of(list1, 0));
//   }
//
//   /** 获取一套模板牌 */
//   public static Pair<List<LinkedList<Poker>>, Integer> getTmpPokers(int templateId) {
//     // 1. 错误返回1：用的是同一个 list 和 LinkedList 对象，
//     // return pokerListTempMap.get(templateId);
//     Pair<List<LinkedList<Poker>>, Integer> pair = pokerListTempMap.get(templateId);
//     // 2. 错误返回2：用的是同一个 LinkedList 对象，
//     // return Pair.of(new ArrayList<>(pair.getKey()), pair.getValue());
//
//     List<LinkedList<Poker>> list = new ArrayList<>();
//     for (LinkedList<Poker> links : pair.getKey()) {
//       list.add(new LinkedList<>(links));
//     }
//
//     return Pair.of(list, pair.getValue());
//   }
//
//   public static void main(String[] args) throws InterruptedException {
//     Queue<Pair<List<LinkedList<Poker>>, Integer>> templatePokersListsQueue = new LinkedList<>();
//     // 赋值
//     templatePokersListsQueue.offer(getTmpPokers(1));
//     templatePokersListsQueue.offer(getTmpPokers(1));
//     templatePokersListsQueue.offer(getTmpPokers(1));
//
//     int tag = 0;
//     Pair<List<LinkedList<Poker>>, Integer> usePokerPair = templatePokersListsQueue.poll();
//
//     // TODO print
//     StringBuilder sb = new StringBuilder();
//     sb.append("displayerPokerTag: ").append(usePokerPair.getValue()).append("  AllPokers: ");
//
//     for (int i = 0; i < usePokerPair.getKey().size(); i++) {
//       LinkedList<Poker> pokers = usePokerPair.getKey().get(i);
//       sb.append("tag").append(i).append("=").append(pokers.toString()).append(";");
//     }
//     System.out.println(sb);
//
//     sendPoker(usePokerPair, tag,2);
//
//     Thread.sleep(20000);
//   }
//
//   private static List<Poker> sendPoker(Pair<List<LinkedList<Poker>>, Integer> usePokerPair, int tag, int num) {
//     List<LinkedList<Poker>> pokersList = usePokerPair.getKey();
//     LinkedList<Poker> pokers = pokersList.get(tag);
//
//     List<Poker> tmpPokers = new ArrayList<>(num);
//     for (int i = 0; i < num; i++) {
//       tmpPokers.add(pokers.poll());
//     }
//     return tmpPokers;
//   }
//
//
// }
