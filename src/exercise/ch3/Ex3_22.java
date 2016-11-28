package exercise.ch3;

import java.util.Scanner;

import util.MyStack;

/**
 * 计算后缀表达式
 */
public class Ex3_22 {
	public static void main(String[] args) {
		System.out.println(evalPostFix());
	}
	static double evalPostFix() {
		MyStack<Double> s = new MyStack<>();
		String token;
		double a, b, result = 0.0;
		boolean isNumber;

		Scanner sc = new Scanner(System.in);
		token = sc.next();
		while (token.charAt(0) != '=') {
			try {
				isNumber = true;
				result = Double.parseDouble(token);
			} catch (Exception e) {
				isNumber = false;
			}
			if (isNumber) {
				s.push(result);
			} else {
				switch (token.charAt(0)) {
				case '+':
					b = s.pop();
					a = s.pop();
					s.push(a + b);
					break;
				case '-':
					b = s.pop();
					a = s.pop();
					s.push(a - b);
					break;
				case '*':
					b = s.pop();
					a = s.pop();
					s.push(a * b);
					break;
				case '/':
					b = s.pop();
					a = s.pop();
					s.push(a / b);
					break;
				case '^':
					b = s.pop();
					a = s.pop();
					s.push(Math.pow(a, b));
					break;
				}
			}
			token = sc.next();
		}
		return s.pop();
	}
}
