package demo.ch4;

import java.util.Map;
import java.util.TreeMap;

import util.MyArrayList;

/**
 * 作为TreeMap的一个例子：找出通过单个字母替换可以变成至少15个其他单词的单词
 * 
 * @author lee
 *
 */
public class TreeMapCase {
	
	//打印出至少有minWords个可替换单词的单词
	public static void printHighChangeables(Map<String, MyArrayList<String>> adjWords, int minWords) {
		for (Map.Entry<String, MyArrayList<String>> entry : adjWords.entrySet()) {
			MyArrayList<String> words = entry.getValue();
			
			if (words.size() >= minWords) {
				System.out.print(entry.getKey() + "(");
				System.out.print(words.size() + "):");
				for (String w : words) {
					System.out.print(" " + w);
				}
				System.out.println();
			} else {
				;
			}
		}
	}
	
	//计算一个Map，其key为单词，其值为与该单词相差一个字母的单词的集合
	//时间复杂度为O(n^2)，因为需要嵌套迭代
	public static Map<String, MyArrayList<String>> computeAdjacentWords(MyArrayList<String> theWords) {
		Map<String, MyArrayList<String>> adjWords = new TreeMap<>();
		
		String[] words = new String[theWords.size()];//确定大小的遍历用数组，可以节省空间
		for (int i = 0; i < words.length; i++) {
			words[i] = theWords.get(i);
		}
		
		for (int i = 0; i < words.length; i++) {
			for (int j = i + 1; j <words.length; j++) {
				if (oneCharOff(words[i], words[j])) {
					update(adjWords, words[i], words[j]);
					update(adjWords, words[j], words[i]);
				}
			}
		}
		
		return adjWords;
	}
	
	private static void update(Map<String, MyArrayList<String>> m, String key, String value) {
		MyArrayList<String> words = m.get(key);
		
		if (words == null) {//约定key-value，value为空代表key也为空
			words = new MyArrayList<>();
			m.put(key, words);
		} else {
			;
		}
		words.add(value);
	}
	
	//返回true如果word1和word2有相同的长度，并且只有一个字符不同
	private static boolean oneCharOff(String word1, String word2) {
		if (word1.length() != word2.length()) {
			return false;
		}
		
		int diffs = 0; //字符不同的数量
		
		for (int i = 0; i < word1.length(); i++) {
			if (word1.charAt(i) != word2.charAt(i)) {
				diffs++;
				if (diffs > 1) {
					return false;
				} else {
					;
				}
			} else {
				;
			}
		}
		
		return diffs == 1;
	}
	
	public static void main(String[] args) {
		
		MyArrayList<String> theWords = new MyArrayList<>();
		
		theWords.add("head");theWords.add("heap");theWords.add("lead");theWords.add("wine");theWords.add("wife");
		theWords.add("wifi");theWords.add("wing");theWords.add("ling");theWords.add("mine");
		
		Map<String, MyArrayList<String>> adjWords =computeAdjacentWords(theWords);
		
		printHighChangeables(adjWords, 0);
		
	}
}
