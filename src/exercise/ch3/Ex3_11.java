package exercise.ch3;

/**
 * 单链表，有头节点无尾节点
 * @author lee
 *
 * @param <Object>
 */
public class Ex3_11{
	//当前项数
	private int theSize;
	//header节点和tail节点
	private Node<Object> head;
	//节点类
	@SuppressWarnings("hiding")
	private class Node<Object>{
		public Object data;
		public Node<Object> next;
		public Node(Object data, Node<Object> next) {
			this.data = data;
			this.next = next;
		}
	}
	
	public Ex3_11() {
		init();
	}
	
	private void init() {
		theSize = 0;
		head = new Node<Object>(null, null);
	}
	
	//返回链表大小
	public int size() {
		return theSize;
	}
	
	//打印链表
	public void print() {
		Node<Object> p = head.next;
		while (p != null) {
			System.out.print(p.data + " ");
			p = p.next;
		}
	}
	
	//测试值x是否含于链表的方法
	public boolean contains(Object x) {
		Node<Object> p = head.next;
		while (p != null) {
			if (x.equals(p.data)) {
				return true;
			} else {
				p = p.next;
			}
		}
		return false;
	}
	
	//如果值x尚未含于链表，添加值x到该链表的方法
	public boolean add(Object x) {
		if (contains(x)) {
			return false;
		} else {
			//添加到头部
			Node<Object> p = new Node<Object>(x, null);
			p.next = head.next;
			head.next = p;
			theSize ++;
			return true;
 		}
	}
	
	//如果值x于链表，将x从该链表中删除
	public boolean remove(Object x) {
		if (!contains(x)) {
			return false;
		} else {
			Node<Object> curr = head.next;
			Node<Object> prev = head;
			while (curr != null) {
				if (x.equals(curr.data)) {
					prev.next = curr.next;
					theSize --;
					return true;
				} else {
					prev = curr;
					curr = curr.next;
				} 
			}
			return false;
		}
	}
}
