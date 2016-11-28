package exercise.ch3;

import util.MyStack;


/**
 * 检测平衡符号
 */
public class Ex3_21 {
	public static void main(String[] args) {
		try {
			balanceSymbol("{12321(2");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String balanceSymbol(String text) throws Exception {
		char[] arr = text.toCharArray();
		//制造一个空栈
		MyStack<Character> items = new MyStack<>();
		for (int i = 0; i < arr.length; i++) {
			if ('(' == arr[i] || '{' == arr[i] || '[' == arr[i]) {
				items.push(arr[i]);
			}
			if (')' == arr[i] || '}' == arr[i] || ']' == arr[i]) {
				if (items.size() == 0) {
					throw new Exception("没有对应的开放符号！");
				} else {
					Character top = items.pop();
					if (top != arr[i]) {
						throw new Exception("开放符号不匹配，请检查是否产生符号嵌套，或者符号缺失！");
					}
				}
			}
		}
		if (items.size() != 0) {
			throw new Exception("末尾开放符号多余！");
		}
		return "";
	} 
}
