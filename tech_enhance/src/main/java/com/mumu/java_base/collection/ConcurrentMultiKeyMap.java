// package com.mumu.java_base.collection;
//
// import java.util.Collection;
// import java.util.Collections;
// import java.util.Map;
// import java.util.Objects;
// import java.util.Optional;
// import java.util.Set;
// import java.util.concurrent.ConcurrentHashMap;
// import java.util.concurrent.ConcurrentMap;
// import java.util.stream.Collectors;
//
// /** 线程安全的多keymap。如果要用非线程安全的就直接用guava的Table @Date: 2024/8/7 上午11:33 @Author: xu.hai */
// public class ConcurrentMultiKeyMap<R, C, V> {
//   private final int expectedRow;
//   private final int expectedColumn;
//
//   private final ConcurrentMap<R, Map<C, V>> tableMap;
//
//   private ConcurrentMultiKeyMap(int expectedRow, int expectedColumn) {
//     this.expectedRow = expectedRow;
//     this.expectedColumn = expectedColumn;
//     tableMap = new ConcurrentHashMap<>(this.expectedRow);
//   }
//
//   private ConcurrentMultiKeyMap() {
//     this(16, 16);
//   }
//
//   public static <R, C, V> ConcurrentMultiKeyMap<R, C, V> create() {
//     return new ConcurrentMultiKeyMap<>();
//   }
//
//   public static <R, C, V> ConcurrentMultiKeyMap<R, C, V> create(
//       int expectedRow, int expectedColumn) {
//     return new ConcurrentMultiKeyMap<>(expectedRow, expectedColumn);
//   }
//
//   public Set<R> rowKeySet() {
//     return tableMap.keySet();
//   }
//
//   /** 不会为null，key不存在返回空集合，不可直接操作返回 map */
//   public Map<C, V> row(R rowKey) {
//     Objects.requireNonNull(rowKey);
//     return tableMap.getOrDefault(rowKey, Collections.emptyMap());
//   }
//
//   /** 不会为null，可对返回map直接操作 */
//   public Map<C, V> rowNew(R rowKey) {
//     Objects.requireNonNull(rowKey);
//     return tableMap.computeIfAbsent(rowKey, k -> new ConcurrentHashMap<>(this.expectedColumn));
//   }
//
//   public Set<C> columnKeySet() {
//     return tableMap.values().stream().flatMap(m -> m.keySet().stream()).collect(Collectors.toSet());
//   }
//
//   public Map<R, V> column(C c) {
//     Objects.requireNonNull(c);
//     return tableMap.entrySet().stream()
//         .filter(e -> e.getValue().containsKey(c))
//         .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().get(c)));
//   }
//
//   public void put(R r, C c, V v) {
//     Objects.requireNonNull(r);
//     Objects.requireNonNull(c);
//     tableMap.computeIfAbsent(r, k -> new ConcurrentHashMap<>(this.expectedColumn)).put(c, v);
//   }
//
//   public boolean contains(R r, C c) {
//     Objects.requireNonNull(r);
//     Objects.requireNonNull(c);
//     return tableMap.getOrDefault(r, Collections.emptyMap()).containsKey(c);
//   }
//
//   public V get(R r, C c) {
//     Objects.requireNonNull(r);
//     Objects.requireNonNull(c);
//     return tableMap.getOrDefault(r, Collections.emptyMap()).get(c);
//   }
//
//   public V remove(R r, C c) {
//     Objects.requireNonNull(r);
//     Objects.requireNonNull(c);
//     return Optional.ofNullable(row(r)).map(m -> m.remove(c)).orElse(null);
//   }
//
//   public void remove(R r) {
//     Objects.requireNonNull(r);
//     tableMap.remove(r);
//   }
//
//   public void clear() {
//     tableMap.clear();
//   }
//
//   public boolean isEmpty() {
//     return tableMap.isEmpty();
//   }
//
//   public Collection<V> values() {
//     return tableMap.values().stream().flatMap(m -> m.values().stream()).toList();
//   }
// }
