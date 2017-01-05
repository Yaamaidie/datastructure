package util;

/**
 * AVL树的插入和删除
 */
public class AvlTree<T extends Comparable<? super T>> {
	public static void main(String[] args) {
		AvlTree<Integer> t = new AvlTree<>();
		for (int i = 0; i < 20000; i++) {
			t.insert(i);
		}
		t.printTree();
		t.check();
	}
	
	//允许的最大高度差
	public static final int ALLOW_IMBALANCE = 1;
	
	private AvlNode<T> root;
	
	public AvlTree() {
		root = null;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void insert(T x) {
		root = insert(x, root);
	}
	
	public void remove(T x) {
		root = remove(x, root);
	}
	
	/**
	 * 检查avl中的高度信息是否正确并且平衡性质是否成立
	 * @return
	 */
	public boolean check() {
		return check(root);
	}
	
	/**
	 * 打印
	 * @param node
	 * @return
	 */
	public void printTree() {
		if (isEmpty()) {
			System.out.println("Empty tree");
		} else {
			printTree(root);
		}
	}
	
	private void printTree(AvlNode<T> node) {
		if (node != null) {
			printTree(node.left);
			System.out.println(node.element);
			printTree(node.right);
		}
	}
	
	private boolean check(AvlNode<T> node) {
		boolean okHeight, okBalance;//当前节点的高度和平衡信息
		if (height(node) == Math.max(height(node.left), height(node.right)) + 1) {
			okHeight = true;
		} else {
			return false;
		}
		if (Math.abs(height(node.left) - height(node.right)) <= ALLOW_IMBALANCE) {
			okBalance = true;
		} else {
			return false;
		}

		if (okHeight && okBalance) {
			return (check(node.left) && check(node.right));
		} else {
			return false;
		}
	}
	
	private int height(AvlNode<T> node) {
		return node == null ? -1 : node.height;
	}
	
	private AvlNode<T> insert(T x, AvlNode<T> node) {
		if (node == null) {
			return new AvlNode<T>(x);
		}
		
		int compareResult = x.compareTo(node.element);
		
		if (compareResult < 0) {
			node.left = insert(x, node.left);
		} else if (compareResult > 0) {
			node.right = insert(x, node.right);
		} else {
			;
		}
		return balance(node);
	}
	
	private AvlNode<T> remove(T x, AvlNode<T> node) {
		if (node == null) {
			return node;
		}
		
		int compareResult = x.compareTo(node.element);
		
		if (compareResult < 0) {
			node = remove(x, node.left);
		} else if (compareResult > 0) {
			node = remove(x, node.right);
		} else {
			if (node.left != null && node.right != null) { //两个儿子
				node.element = findMin(node.right).element; //节点上的对象是该节点的右子树的最小的对象
				node.right = remove(node.element, node.right); //节点的右子树需要删掉最小的节点
			} else {
				node = (node.left != null) ? node.left : node.right;//被删除的节点的左儿子存在，则用左儿子替换该节点，否则用右儿子替换
			}	
		}
		return node;
	}
	
	private AvlNode<T> findMin(AvlNode<T> node) {
		if (node == null) {
			return node;
		} else if (node.left == null) {
			return node;
		} else {
			return findMin(node.left);
		}
	}
	
	//假定node是要么是平衡的，要么至少有一个子节点是平衡的
	private AvlNode<T> balance(AvlNode<T> node) {
		if (node == null) {
			return node;
		}
		
		if (height(node.left) - height(node.right) > ALLOW_IMBALANCE) {
			if (height(node.left.left) >= height(node.left.right)) { //第一种情况：左儿子的左子树插入
				node = rotateWithLeftChild(node);
			} else { //第二种情况：左儿子的右子树插入
				node = doubleWithLeftChild(node);
			}
		} else if (height(node.right) - height(node.left) > ALLOW_IMBALANCE) {
			if (height(node.right.left) >= height(node.right.right)) { //第三种情况：右儿子的左子树插入
				node = doubleWithRightChild(node);
			} else { //第四种情况：右儿子的右子树插入
				node = rotateWithRightChild(node);
			}
		}
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		return node;
	}
	
	private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2) { //k2的命名来自书上的"调整情形1的单选择"示意图
		AvlNode<T> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), k2.height) + 1;
		return k1;
	}
	
	private AvlNode<T> rotateWithRightChild(AvlNode<T> k2) {
		AvlNode<T> k1 = k2.right;
		k2.right = k1.left;
		k1.left = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(k2.height, height(k1.right)) + 1; 
		return k1;
	}
	
	private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3) { //k3的命名来自书上的"调整情形1的单选择"示意图
		k3.left = rotateWithRightChild(k3.left);
		return rotateWithLeftChild(k3);
	}
	
	private AvlNode<T> doubleWithRightChild(AvlNode<T> k3) {
		k3.right = rotateWithLeftChild(k3.right);
		return rotateWithRightChild(k3);
	}
	
	private static class AvlNode<AnyType> {
		AnyType element; //节点中的数据
		AvlNode<AnyType> left; //左子节点
		AvlNode<AnyType> right; //右子节点
		int height; //树的高度
		
		AvlNode(AnyType theElement) {
			this(theElement, null, null);
		}
		
		AvlNode(AnyType theElement, AvlNode<AnyType> leftBranch, AvlNode<AnyType> rightBranch) {
			element = theElement;
			left = leftBranch;
			right= rightBranch;
			height = 0;
		}
	} 
}
