package com.demo.nuike.chuji.class_05;

import java.util.HashMap;

public class Code_02_RandomPool {  // 设计RandomPool结构

	public static class Pool<K> {	// 准备两个hash表，两张表的key与value反着存	，size表示表中有几个同桶 ，做标记作用
		private HashMap<K, Integer> keyIndexMap;
		private HashMap<Integer, K> indexKeyMap;
		private int size;

		public Pool() {
			this.keyIndexMap = new HashMap<K, Integer>();
			this.indexKeyMap = new HashMap<Integer, K>();
			this.size = 0;
		}

		public void insert(K key) {	// 插入操作：
			if (!this.keyIndexMap.containsKey(key)) {
				this.keyIndexMap.put(key, this.size);
				this.indexKeyMap.put(this.size++, key); // this.size++:表示size在给表赋值后，size要+1
			}
		}

		public void delete(K key) {	// 删除思路：从表一中拿出最后一个key与要随机删除的那个key的位置去填补上去（交换），
			if (this.keyIndexMap.containsKey(key)) { // 这样就保证了hash表的的连续性，不会因为下次随机同一个位置时产生空值导致一直要继续随机
				int deleteIndex = this.keyIndexMap.get(key);	// 从表一key值获取要删除的序号
				int lastIndex = --this.size;	// 拿到最后位置的序号，并且size的大小要-1
				K lastKey = this.indexKeyMap.get(lastIndex);	// 从表二中通过最后序号拿到最后面的key值

				this.keyIndexMap.put(lastKey, deleteIndex);		// 把表中末尾元素的值赋到表一删除的位置上(key,index)
				this.indexKeyMap.put(deleteIndex, lastKey);		// 把表中末尾元素的值赋到表一删除的位置上(index,key)

				this.keyIndexMap.remove(key);	// 两张表都删除掉最后那个序号表示的元素。
				this.indexKeyMap.remove(lastIndex);
			}
		}

		public K getRandom() {	// 随机那表中的值
			if (this.size == 0) {
				return null;
			}
			int randomIndex = (int) (Math.random() * this.size); // 0 ~ size -1
			return this.indexKeyMap.get(randomIndex);
		}

	}

	public static void main(String[] args) {
		Pool<String> pool = new Pool<String>();
		pool.insert("zuo");
		pool.insert("cheng");
		pool.insert("yun");
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());

	}

}
