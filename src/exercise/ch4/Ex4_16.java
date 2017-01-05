package exercise.ch4;

/**
 * 重做二叉查找树实现懒惰删除
 * @author lee
 *
 */
public class Ex4_16 {
	
	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		for (int i = 0; i < 50; i = i + 2) {
			tree.insert(i);
		}
		System.out.println(tree.findMax());
		System.out.println(tree.findMin());
		System.out.println(tree.contains(7));
		System.out.println(tree.contains(8));
		tree.insert(55);
		System.out.println(tree.findMax());
		
		tree.remove(55);
		System.out.println(tree.findMax());
		tree.remove(0);
		System.out.println(tree.findMin());
	}
	
}

class BinarySearchTree<T extends Comparable<? super T>> {
	
	private BinaryNode<T> root;
	
	public BinarySearchTree() {
		root = null;
	}
	
	public BinarySearchTree(T t) {
		root = new BinaryNode<T>(t);
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void makeEmpty() {
		root = null;
	}
	
	public boolean contains(T x) {
		return contains(x, root);
	}
	
	public T findMin() {
		if (root == null) {
			return null;
		} else {
			BinaryNode<T> result = findMin(root, null);
			if (result == null) {
				throw new java.util.NoSuchElementException();
			}
			return result.element;
		}
	}	
	
	public T findMax() {
		if (root == null) {
			return null;
		} else {
			BinaryNode<T> result = findMax(root, null);
			if (result == null) {
				throw new java.util.NoSuchElementException();
			}
			return result.element;
		}
	}
	
	public void insert(T x) {
		root = insert(x, root);
	}
	
	public void remove(T x) {
		root = remove(x, root);
	}
	
	private BinaryNode<T> remove(T x, BinaryNode<T> tree) {
		if (tree == null) {
			return tree;
		}
		
		int compareResult = x.compareTo(tree.element);
		
		if (compareResult < 0) {
			tree.left = remove(x, tree.left);
		} else if (compareResult > 0) {
			tree.right = remove(x, tree.right);
		} else {
			tree.removed = true;//标记为删除
		}
		
		return tree;
	} 
	
	private BinaryNode<T> insert(T x, BinaryNode<T> tree) {
		if (tree == null) {
			return new BinaryNode<T>(x, null, null);
		}
		
		int compareResult = x.compareTo(tree.element);
		
		if (compareResult < 0) {
			tree.left =  insert(x, tree.left);
		} else if (compareResult > 0) {
			tree.right = insert(x, tree.right);
		} else {
			;
		}
		
		return tree;
	}
	
	private BinaryNode<T> findMax(BinaryNode<T> tree, BinaryNode<T> max) {//max存非removed的当前最大值节点
		if (tree == null) {
			return max;
		} else {
			max = (tree.removed == false ? tree : max);
			return findMax(tree.right, max);
		}
	}
	
	private BinaryNode<T> findMin(BinaryNode<T> tree, BinaryNode<T> min) {
		if (tree == null) {
			return min;
		} else {
			min = (tree.removed == false ? tree : min);
			return findMin(tree.left, min);
		}
	}
	
	private boolean contains(T x, BinaryNode<T> tree) {
		if (tree == null) {
			return false;
		}
		
		int compareReault = x.compareTo(tree.element);
		
		if (compareReault < 0) {
			return contains(x, tree.left);
		} else if(compareReault > 0) {
			return contains(x, tree.right);
		} else if (tree.removed == true) {
			return false;
		} else {
			return true;
		}
	}
	
	private static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;
		//标记删除状态
		boolean removed = false;
		
		BinaryNode(E e) {
			this(e, null, null);
		}
		
		BinaryNode(E e, BinaryNode<E> l, BinaryNode<E> r) {
			element = e;
			left = l;
			right = r;
		}
	}
	
}
