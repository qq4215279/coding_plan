package chuji.class_07;

public class Code_01_TrieTree {

	public static class TrieNode {//前缀树：		这题字母放在边上，不用放在节点上
		public int path;	//有多少字符串到达过，既生成树过程中的字母出现（经过）的次数
		public int end;		//有多少字符串在这个节点结尾
		public TrieNode[] nexts;	//表示一个字符串生成一条路

		public TrieNode() {
			path = 0;
			end = 0;
			nexts = new TrieNode[26];	//从树的头部开始，可能会生成26条路，因为有26中类型字符
		}
	}

	public static class Trie {	//插入形成树
		private TrieNode root;	//树的头部

		public Trie() {
			root = new TrieNode();
		}

		public void insert(String word) {
			if (word == null) {
				return;
			}
			char[] chs = word.toCharArray();
			TrieNode node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';//比如：chs[i]为a，则index=0。表示了用0-25表示生成的路种类
				if (node.nexts[index] == null) {//判断当前节点有没有走向当前字母的路，
					node.nexts[index] = new TrieNode();//没有，这行代码建出来
				}
				node = node.nexts[index];	//有，node跳到下一个节点，并且node++,path++
				node.path++;
			}
			node.end++;	//到达最后一个字符时，记录结尾字母（既让end加1）
		}

		public void delete(String word) {	//在数中删除一个word
			if (search(word) != 0) {	//先判断word是否存在
				char[] chs = word.toCharArray();
				TrieNode node = root;
				int index = 0;
				for (int i = 0; i < chs.length; i++) {
					index = chs[i] - 'a';
					if (--node.nexts[index].path == 0) {//判断当前node所对应的path个数出现了几次，如果为0，
						node.nexts[index] = null;	//则该node后面的node就不用再往下跑了，后面的node直接设为赋值为空
						return;	//再返回
					}
					node = node.nexts[index];//如果大于0，则node继续往下跑（因为--node的存在）
				}
				node.end--;//跑到最下面一个node，
			}
		}

		public int search(String word) {	//查字符串出现过几次
			if (word == null) {
				return 0;
			}
			char[] chs = word.toCharArray();
			TrieNode node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.end;
		}

		public int prefixNumber(String pre) {
			if (pre == null) {
				return 0;
			}
			char[] chs = pre.toCharArray();
			TrieNode node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.path;
		}
	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		System.out.println(trie.search("zuo"));
		trie.insert("zuo");
		System.out.println(trie.search("zuo"));
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.insert("zuo");
		trie.insert("zuo");
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.insert("zuoa");
		trie.insert("zuoac");
		trie.insert("zuoab");
		trie.insert("zuoad");
		trie.delete("zuoa");
		System.out.println(trie.search("zuoa"));
		System.out.println(trie.prefixNumber("zuo"));

	}

}
