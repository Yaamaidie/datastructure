package exercise.ch4;

/**
 * 递归的线性算法，测试一棵二叉树是否在每一个节点都满足查找树的序的性质
 * @author lee
 *
 */
public class Ex4_32 {
	
	static <E extends Comparable<? super E>> boolean check(Node<E> t) {
		boolean isOrder;//有序：当前根节点的值在左子树最大值和右子树最小值之间
		if (t.left != null && t.right != null) {
			isOrder = findMax(t.left).element.compareTo(t.element) < 0
					&& findMin(t.right).element.compareTo(t.element) > 0;
			if (!isOrder) {
				return false;
			} else {
				return check(t.left) && check(t.right);
			}
		} else if (t.left == null && t.right != null) {
			isOrder = findMin(t.right).element.compareTo(t.element) > 0;
			if (!isOrder) {
				return false;
			} else {
				return check(t.right);
			}
		} else if (t.right == null && t.left != null) {
			isOrder = findMax(t.left).element.compareTo(t.element) < 0;
			if (!isOrder) {
				return false;
			} else {
				return check(t.left);
			}
		} else {
			return true;
		}
		
	}
	
	private static <E> Node<E> findMin(Node<E> t) {
		if (t == null) {
			throw new NullPointerException();
		}
		
		if (t.left == null) {
			return t;
		} else {
			return findMin(t.left);
		}
	}
	
	private static <E> Node<E> findMax(Node<E> t) {
		if (t == null) {
			throw new NullPointerException();
		}
		
		if (t.right == null) {
			return t;
		} else {
			return findMax(t.right);
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
