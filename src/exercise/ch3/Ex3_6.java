package exercise.ch3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Ex3_6 {
	public static void main(String[] args) {
		pass(1, 10000, new ArrayList<Integer>());
		pass(1, 100000, new ArrayList<Integer>());
		pass(1, 1000000, new ArrayList<Integer>());
		pass(1, 10000, new LinkedList<Integer>());
		pass(1, 100000, new LinkedList<Integer>());
		pass(1, 1000000, new LinkedList<Integer>());
		pass(1, 10000000, new LinkedList<Integer>());
	}
	/**
	 * 基本做法：对于n个人，向前传递，每隔m次就删掉那个人，把球给下一个人
	 * 两个改进：1.对于m很大（至少大于n时），传递m次等价于传递(m mod n)次；
	 * 		    2.对于m大于n/2且小于等于n（1.保证了这一点），向前传递m次等价于向后传递n-m次
	 * @param m
	 * @param n
	 */
	public static void pass(int m, int n, List<Integer> items) {
		Long begin = System.currentTimeMillis();
		for (int i = 1; i <= n; i++) {
			items.add(i);
		}

		ListIterator<Integer> iter = items.listIterator();
		
		//用currentItems保存当前剩余人数；
		//mPrime保存当前实际传递次数(mPrime = m mod currentItems)，循环完mPrime次时，删除并打印当前项；
		int currentItems = n;
		int mPrime = m % n;
		int item = 0;//保存每次删除的项
		for (int i = 0; i < n; i++) {//有n个人，就要循环n次，每次减少一个人
			mPrime = m % currentItems;
			if ( mPrime <= currentItems / 2) {
				if (iter.hasNext()) {
					item = iter.next();
				}
				for (int j = 0; j < mPrime; j++) {
					if (!iter.hasNext()) {//到了tail则重置迭代器，从head开始
						iter = items.listIterator();
					}
					item = iter.next();
				}
			} else {
				for (int j = 0; j < currentItems - mPrime; j++) {
					if (!iter.hasPrevious()) {
						iter = items.listIterator(items.size()); //到了head则重置迭代器，从tail开始
					}
					item = iter.previous();
				}
			}
//			System.out.println("Remove:" + item);
			iter.remove();
			if (!iter.hasNext()) {
				iter = items.listIterator();
			}
			
			currentItems--;
		}
		Long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}
}
