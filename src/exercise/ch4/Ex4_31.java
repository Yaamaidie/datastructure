package exercise.ch4;

public class Ex4_31 {
	public static void main(String[] args) {
		
	}
	
	static int countNodes(Node<?> t) {
		if (t == null) {
			return 0;
		} else {
			return 1 + countNodes(t.left) + countNodes(t.right);
		}
	}
	
	static int countLeaves(Node<?> t) {
		if (t == null) {
			return 0;
		} else if (t.left == null & t.right == null) {
			return 1;
		} else {
			return countLeaves(t.left) + countLeaves(t.right);
		}
	}
	
	static int countFull(Node<?> t) {
		if (t == null) {
			return 0;
		} else {
			int tIsFull = (t.left != null && t.right != null) ? 1 : 0;
			return tIsFull + countFull(t.left) + countFull(t.right);
		}
	}
	
	
	private static class Node<E> {
		E element;
		Node<E> left;
		Node<E> right;
		
		Node(E e) {
			this(e, null, null);
		}
		
		Node(E e, Node<E> l, Node<E> r) {
			element = e;
			left = l;
			right = r;
		}
	}
}
