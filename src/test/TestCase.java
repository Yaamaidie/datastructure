package test;

import static org.junit.Assert.*;

import org.junit.Test;

import demo.ch2.Foo;
import demo.ch2.GenericMemoryCell;
import exercise.ch3.Ex3_24;


public class TestCase {
	@Test
	public void 泛型类() throws Exception {
		GenericMemoryCell<String> memoryCell = new GenericMemoryCell<String>();
		memoryCell.write(new String("123"));
		System.out.println(memoryCell.read());
	}
	@Test
	public void 类型限界() throws Exception {
		Foo.findMax(new Integer[]{1, 2 ,3});
		Foo.findMax(new String[]{"123", "456", "789"});
	}
	@Test
	public void 一个数组实现两个栈() throws Exception {
		Ex3_24<Object> s1 = Ex3_24.getFirstStack();
		for (int i = 0; i < 5; i++) {
			s1.push(i);
			System.out.println(s1.peek());
		}
		Ex3_24<Object> s2 = Ex3_24.getSecondStack();
		for (int i = 5; i < 10; i++) {
			s2.push(i);
			System.out.println(s2.peek());
		}
	}
}
