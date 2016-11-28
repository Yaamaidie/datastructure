package exercise.ch3;

import java.util.Scanner;

import util.MyArrayList;
import util.MyStack;

public class Ex3_23 {
	public static final char[] operators = {'(', ')', '+', '-', '*', '/', '^'};
	public static void main(String[] args) {
		MyArrayList<Character> infix = new MyArrayList<>();
		infix = InfixToPostfix();
		for (Object obj : infix) {
			System.out.print(obj + " ");
		}
	}
	// 中缀转后缀 (, ), +, -, *, /, ^
	static MyArrayList<Character> InfixToPostfix() {
		MyStack<Character> s = new MyStack<>();
		MyArrayList<Character> infix = new MyArrayList<>();
		String expression;//操作符或操作数字符串
		Character token;//操作符或者操作数
		int i = 0;
		
		Scanner sc = new Scanner(System.in);
		expression = sc.nextLine();
		while ((token = expression.charAt(i++)) != '=') {
			boolean isOperetor = false;
			for (int j = 0; j < operators.length; j++) {
				if (operators[j] == token) {
					isOperetor = true;
				}
			}
			if (!isOperetor) {
				infix.add(token);
			} else {
				switch (token) {
				case ')':
					while (!s.empty() && s.peek() != '(') {
						infix.add(s.pop());
					}
					s.pop();// 弹出'('
					break;
				case '(':
					s.push(token);
					break;
				case '^'://右结合
					while (!s.empty() && s.peek() != '^' && s.peek() != '(') {	
						infix.add(s.pop());
					}
					s.push(token);
					break;
				case '*':// '*'和'/'优先级相同
				case '/':
					while (!s.empty()
							&& s.peek() != '+' && s.peek() != '-' && s.peek() != '(') {
						infix.add(s.pop());
					}
					s.push(token);
					break;
				case '+':
				case '-':
					while (!s.empty() && s.peek() != '(') {
						infix.add(s.pop());
					}
					s.push(token);
					break;
				}
			}
		}
		while (!s.empty()) {
			infix.add(s.pop());
		}
		return infix;
	}
}
