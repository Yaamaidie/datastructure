package exercise.ch3;

public class Ex3_2 {
	//双链表节点
	private static class Node<T> {
		@SuppressWarnings("unused")
		public Node(T d, Node<T> p, Node<T> n) {
			data = d;
			prev = p;
			next = n;
		}

		@SuppressWarnings("unused")
		public T data;
		@SuppressWarnings("unused")
		public Node<T> prev;
		public Node<T> next;
	}
	// 单链表(不想麻烦，把只是用Node的next链可以简单当作单链表)
	public <T> void swapWithNext(Node<T> beforeP) {
		Node<T> p, afterP;
		
		p = beforeP.next;
		afterP = p.next;
		
		p.next = afterP.next;
		beforeP.next = afterP;
		afterP.next = p;
	}
	//双链表
	public <T> void swapWithNext2(Node<T> p) {
		Node<T> beforeP, afterP;
		beforeP = p.prev;
		afterP = p.next;
		
		p.next = afterP.next;
		beforeP.next = afterP;
		afterP.next = p;
		p.next.prev =p;
		p.prev = afterP;
		afterP.prev = beforeP;
	}
}
