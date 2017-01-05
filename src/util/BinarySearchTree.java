package util;

/**
 * 二叉查找树ADT
 */
public class BinarySearchTree<T extends Comparable<? super T>> {
	
	private BinaryNode<T> root;
	
	//constructor
	public BinarySearchTree() {
		root = null;
	}
	
	public void makeEmpty() {
		root = null;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public boolean contains(T x) {
		return contains(x, root);
	} 
	
	public T findMin() {
		if (isEmpty()) {
			throw new RuntimeException("树为空");
		}
		
		BinaryNode<T> result = findMin(root);
		if (result == null) {
			throw new java.util.NoSuchElementException();
		} 
		return result.element;
	}
	
	public T findMax() {
		if (isEmpty()) {
			throw new RuntimeException("树为空");
		}
		
		BinaryNode<T> result = findMax(root);
		if (result == null) {
			throw new java.util.NoSuchElementException();
		}
		return result.element;
	}
	
	public void insert(T t) {
		//插入后将root更新
		root = insert(t, root);
	}
	
	public void remove(T t) {
		root = remove(t, root);
	}
	
	public void printTree() {
		
	}
	
	private boolean contains(T x, BinaryNode<T> tree) {
		if (tree == null) {
			return false;
		}
		
		int compareResult = x.compareTo(tree.element);
		
		if (compareResult < 0 ) {
			return contains(x, tree.left);
		} else if (compareResult > 0) {
			return contains(x, tree.right);
		} else {
			return true;
		}
	}
	
	private BinaryNode<T> findMin(BinaryNode<T> tree) {
		if (tree == null) {
			return null;
		} else if (tree.left == null) {
			return tree;
		} else {
			return findMin(tree.left);
		}
	}
	
	private BinaryNode<T> findMax(BinaryNode<T> tree) {
		if (tree != null) {
			while (tree.right != null) {
				tree = tree.right;
			}
		}
		
		return tree;
	} 
	
	//返回插入操作后更新过的树
	private BinaryNode<T> insert(T x, BinaryNode<T> tree) {
		if (tree == null) {
			return new BinaryNode<T>(x, null, null);
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
	
	//返回删除操作更新过的树
	private BinaryNode<T> remove(T x, BinaryNode<T> tree) {
		if (tree == null) {
			return tree;
		}
		
		int compareResult = x.compareTo(tree.element);
		
		if (compareResult < 0) {
			tree.left = remove(x, tree.left);
		} else if (compareResult > 0) {
			tree.right = remove(x, tree.right);
		} else if (tree.left != null && tree.right != null) {//两个子树
			tree.element = findMin(tree.right).element;//节点上的对象是该树的右子树的最小的对象
			tree.right = remove(tree.element, tree.right);//节点的右子树需要删掉最小的节点
		} else {//一个子树或没有子树
			tree = (tree.left != null) ? tree.left : tree.right;//被删除的节点tree的左节点存在，则用左节点替换tree，否则用右节点替换
		}
		return tree;
	}
	
 	//二叉树类
	private static class BinaryNode<AnyType> {
		AnyType element;
		BinaryNode<AnyType> left;
		BinaryNode<AnyType> right;
		
		BinaryNode(AnyType theElement) {
			this(theElement, null, null);
		}
		
		BinaryNode(AnyType theElement, BinaryNode<AnyType> leftBranch, BinaryNode<AnyType> rightBranch) {
			element = theElement;
			left = leftBranch;
			right = rightBranch;
		} 
	}
}
