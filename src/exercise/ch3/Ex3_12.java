package exercise.ch3;

/**
 * 以排序的顺序重复练习3.11 1.add方法不在是add到head后面，而是add到第一次x<curr.data的时候
 * 2.contains方法如果x小于head.next.data则不包含
 */
public class Ex3_12 {
	// 当前项数
	private int theSize;
	// header节点和tail节点
	private Node<Comparable> head;

	// 节点类
	@SuppressWarnings("hiding")
	private class Node<Comparable> {
		public Comparable data;
		public Node<Comparable> next;

		public Node(Comparable data, Node<Comparable> next) {
			this.data = data;
			this.next = next;
		}
	}

	public Ex3_12() {
		init();
	}

	private void init() {
		theSize = 0;
		head = new Node<Comparable>(null, null);
	}

	// 返回链表大小
	public int size() {
		return theSize;
	}

	// 打印链表
	public void print() {
		Node<Comparable> p = head.next;
		while (p != null) {
			System.out.print(p.data + " ");
			p = p.next;
		}
	}

	// 测试值x是否含于链表的方法
	public boolean contains(Comparable x) {
		Node<Comparable> p = head.next;
		while (p != null && p.data.compareTo(x) < 0) {
			if (x.equals(p.data)) {
				return true;
			} else {
				p = p.next;
			}
		}
		return false;
	}

	// 如果值x尚未含于链表，添加值x到该链表的方法
	public boolean add(Comparable x) {
		if (contains(x)) {
			return false;
		} else {
			Node<Comparable> curr = head.next;
			Node<Comparable> prev = head;
			//当节点的数据大于或者等于x时，结束循环
			while (curr != null && curr.data.compareTo(x) < 0) {
				prev = curr;
				curr = curr.next;	
			}
			Node<Comparable> p = new Node<Comparable>(x, null);
			prev.next = p;
			p.next = curr;
			theSize ++;
			return true;
 		}
	}

	// 如果值x于链表，将x从该链表中删除
	public boolean remove(Comparable x) {
		if (!contains(x)) {
			return false;
		} else {
			Node<Comparable> curr = head.next;
			Node<Comparable> prev = head;
			while (!curr.data.equals(x)) {
				prev = curr;
				curr = curr.next;
			}
			prev.next = curr.next;
			theSize ++;
		}
		return true;
	}
}
