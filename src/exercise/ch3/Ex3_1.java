package exercise.ch3;

import java.util.ArrayList;
import java.util.List;
/**
 * 运行时间为O(n),n为P表的大小
 * @author lee
 *
 */
public class Ex3_1 {
	public void printLots(List<Integer> list1, List<Integer> list2){
		for (int i = 0; i < list2.size(); i++) {
			System.out.println(list1.get(list2.get(i)));
		}
	}
	public static void main(String[] args) {
		Ex3_1 ex3_1 = new Ex3_1();
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			list1.add(i, i);
		}
		list2.add(0, 3);
		list2.add(1, 5);
		list2.add(2, 8);
		ex3_1.printLots(list1, list2);
	}
}
