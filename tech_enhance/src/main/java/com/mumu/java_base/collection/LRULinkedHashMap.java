// package com.mumu.java_base.collection;
//
// import java.io.Serial;
// import java.util.LinkedHashMap;
// import java.util.Map;
// import java.util.concurrent.locks.Lock;
// import java.util.concurrent.locks.ReentrantLock;
// import java.util.function.BiConsumer;
// import java.util.function.BiFunction;
// import java.util.function.Function;
// import java.util.function.Supplier;
// import lombok.Setter;
//
// /**
//  * 固定长度的Map，迭代推荐使用 map.forEach((key, map) -> method())的方式，map.values().forEach/for存在线程安全问题 @Date:
//  * 2024/11/7 上午10:08 @Author: xu.hai
//  */
// public class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {
//
//   @Serial private static final long serialVersionUID = -3791412708654730531L;
//
//   private static final int START_NUMBER = 16;
//
//   private static final float DEFAULT_LOAD_FACTOR = 0.75f;
//
//   /** 最大数量 */
//   private final int max;
//
//   /** 元素移除监听 */
//   @Setter private BiConsumer<K, V> removalListener;
//
//   /** lock */
//   private final Lock lock = new ReentrantLock();
//
//   public LRULinkedHashMap(int max) {
//     this(max, false);
//   }
//
//   /** accessOrder为true时，表示最近最少访问的删除，false则表示移除最早插入的元素 */
//   public LRULinkedHashMap(int max, boolean accessOrder) {
//     super(START_NUMBER, DEFAULT_LOAD_FACTOR, accessOrder);
//     this.max = max;
//   }
//
//   @Override
//   protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
//     boolean shouldRemove = size() > max;
//     if (shouldRemove && removalListener != null) {
//       removalListener.accept(eldest.getKey(), eldest.getValue());
//     }
//     return shouldRemove;
//   }
//
//   @Override
//   public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
//     try {
//       lock.lock();
//       return super.compute(key, remappingFunction);
//     } finally {
//       lock.unlock();
//     }
//   }
//
//   @Override
//   public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
//     try {
//       lock.lock();
//       return super.merge(key, value, remappingFunction);
//     } finally {
//       lock.unlock();
//     }
//   }
//
//   @Override
//   public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
//     try {
//       lock.lock();
//       return super.computeIfAbsent(key, mappingFunction);
//     } finally {
//       lock.unlock();
//     }
//   }
//
//   @Override
//   public void forEach(BiConsumer<? super K, ? super V> action) {
//     try {
//       lock.lock();
//       super.forEach(action);
//     } finally {
//       lock.unlock();
//     }
//   }
//
//   public V get(Object k) {
//     try {
//       lock.lock();
//       return super.get(k);
//     } finally {
//       lock.unlock();
//     }
//   }
//
//   public V put(K key, V value) {
//     try {
//       lock.lock();
//       return super.put(key, value);
//     } finally {
//       lock.unlock();
//     }
//   }
//
//   public V remove(Object key) {
//     try {
//       lock.lock();
//       return super.remove(key);
//     } finally {
//       lock.unlock();
//     }
//   }
//
//   @Override
//   public void clear() {
//     try {
//       lock.lock();
//       super.clear();
//     } finally {
//       lock.unlock();
//     }
//   }
//
//   /**
//    * 获取lru map创建工厂
//    *
//    * @param capacity 容量
//    * @param <K> map中元素类型
//    * @param <V> map中元素类型
//    * @return 创建工程
//    */
//   public static <K, V> Supplier<Map<K, V>> getMapFactory(int capacity) {
//     return () -> of(capacity);
//   }
//
//   /**
//    * 构造 map
//    *
//    * @param capacity 容量
//    * @param <K> map中元素类型
//    * @param <V> map中元素类型
//    * @return map
//    */
//   public static <K, V> Map<K, V> of(int capacity) {
//     return new LRULinkedHashMap<>(capacity);
//   }
//
//   public static <K, V> Map<K, V> of(int capacity, BiConsumer<K, V> removalListener) {
//     LRULinkedHashMap<K, V> map = new LRULinkedHashMap<>(capacity);
//     map.setRemovalListener(removalListener);
//     return map;
//   }
//
//   public static void main(String[] args) {
//     Map<Integer, Integer> map =
//         LRULinkedHashMap.of(10, (k, v) -> System.out.println("remove:" + k));
//     for (int i = 0; i < 15; i++) {
//       map.put(i, i);
//     }
//     System.out.println(map);
//   }
// }
