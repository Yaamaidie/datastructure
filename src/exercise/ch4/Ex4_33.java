package exercise.ch4;

/**
 * 递归方法：该方法使用对树T的根节点的引用而返回从T删除所有树叶所得到的树的根节点的引用
 * @author lee
 *
 */
public class Ex4_33 {
	
	public static void main(String[] args) {
		Node<Integer> root = new Node<Integer>(0);
		for (int i = 1; i < 10; i++) {
			root = Ex4_33.insert(new Integer(i), root);
		}
		printTree(root);
		printTree(removeLeaves(root));
	}
	
	static <T> Node<T> removeLeaves(Node<T> t) {
		if (t == null || (t.left == null && t.right == null)) {
			return null;
		}
		
		t.left = removeLeaves(t.left);
		t.right = removeLeaves(t.right);
		
		return t;
	}
	
	private static <T extends Comparable<? super T>> void printTree(Node<T> t) {
		if (t != null) {
			printTree(t.left);
			System.out.print(t.element);
			printTree(t.right);
		}
	}
	//返回插入操作后更新过的树
	private static <T extends Comparable<? super T>> Node<T> insert(T x, Node<T> tree) {
		if (tree == null) {
			return new Node<T>(x, null, null);
		}
		
		int compareResult = x.compareTo(tree.element);
		
		if (compareResult < 0) {
			tree.left = insert(x, tree.left);
		} else if (compareResult > 0) {
			tree.right = insert(x, tree.right);
		} else {
			;
		}
		return tree;
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
