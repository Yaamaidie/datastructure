package exercise.ch3;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Ex3_4 {
	public static <T extends Comparable<? super T>> void intersection(List<T> list1, List<T> list2, List<T> intersect) {
		ListIterator<T>  iterL1 = list1.listIterator();
		ListIterator<T> iterL2 = list2.listIterator();
		T itemL1 = null, itemL2 =null;
		if (iterL1.hasNext() && iterL2.hasNext()) {
			itemL1 = iterL1.next();
			itemL2 = iterL2.next();
		}
		while (itemL1 != null && itemL2 != null) {
			int result = itemL1.compareTo(itemL2);
			if (result == 0) {
				intersect.add(itemL1);
				itemL1 = iterL1.hasNext() ? iterL1.next() : null;
				itemL2 = iterL2.hasNext() ? iterL2.next() : null;
			} else if (result < 0) {
				itemL1 = iterL1.hasNext() ? iterL1.next() : null;
			} else {
				itemL2 = iterL2.hasNext() ? iterL2.next() : null;
			}
		}
	}
	
	public static void main(String[] args) {
		List<Integer> l1 = new ArrayList<Integer>();
		l1.add(1);
		l1.add(2);
		l1.add(3);
		List<Integer> l2 = new ArrayList<Integer>();
		l2.add(2);
		l2.add(3);
		l2.add(4);
		List<Integer> l3 = new ArrayList<Integer>();
		
		Ex3_4.intersection(l1, l2, l3);
		
		System.out.println(l3);
	}
}
