package demo;

import exercise.ch3.Ex3_24;

public class Demo {
	public static void main(String[] args) {
		Ex3_24<Object> s1 = Ex3_24.getFirstStack();
		for (int i = 0; i < 5; i++) {
			s1.push(i);
		}
		Ex3_24<Object> s2 = Ex3_24.getSecondStack();
		for (int i = 5; i < 10; i++) {
			s2.push(i);
		}
	}
}
