package util;


/**
 * 习题4.11 内部维护一个二叉查找树，在每个节点上添加一个指向父节点的链
 * @author lee
 *
 */
public class MyTreeSet <T extends Comparable<? super T>> implements Iterable<T>{
	public static void main(String[] args) {
		MyTreeSet<Integer> treeSet = new MyTreeSet<>();
		treeSet.insert(8);
		treeSet.insert(5);
		treeSet.insert(7);
		treeSet.insert(3);
		treeSet.insert(9);
		treeSet.printTree();
		for (Integer i : treeSet) {
			System.out.println(i);
		}
	}
	
	private BinaryNode<T> root;
	private int modCount;
	
	public MyTreeSet() {
		root = null;
	}
	
	public void makeEmpty() {
		modCount++;
		root = null;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public boolean contains(T x) {
		return contains(x, root);
	}
	
	public T findMin() throws UnderFlowException {
		if (isEmpty()) {
			throw new UnderFlowException();
		}
		
		return findMin(root).element;
	}
	
	public T findMax() throws UnderFlowException {
		if (isEmpty()) {
			throw new UnderFlowException();
		}
		
		return findMax(root).element;
	}
	
	public void insert(T x) {
		root = insert(x, root, null);
	}
	
	public void remove(T x) {
		root = remove(x, root);
	}
	
	public void printTree() {
		if (isEmpty()) {
			System.out.println("Empty tree");
		} else {
			printTree(root);
		}
	}
	
	private void printTree(BinaryNode<T> tree) {
		if (tree != null) {
			printTree(tree.left);
			System.out.println(tree.element);
			printTree(tree.right);
		} else {
			;
		}
	}
	
	private BinaryNode<T> insert(T x, BinaryNode<T> tree, BinaryNode<T> parentTree) {
		if (tree == null) {
			modCount++;
			return new BinaryNode<T>(x, null, null, parentTree);
		} 
		
		int compareResult = x.compareTo(tree.element);
		
		if (compareResult < 0) {
			tree.left = insert(x, tree.left, tree);
		} else if (compareResult > 0){
			tree.right = insert(x, tree.right, tree);
		} else {
			;
		}
		
		return tree;
	}
	
	private BinaryNode<T> remove(T x, BinaryNode<T> tree) {
		if (tree == null) {
			return null; //没找到
		}
		
		int compareResult = x.compareTo(tree.element);
		
		if (compareResult < 0) {
			tree.left = remove(x, tree.left);
		} else if (compareResult > 0) {
			tree.right = remove(x, tree.right);
		} else if (tree.left != null && tree.right != null){//两个孩子
			tree.element = findMin(tree.right).element;
			tree.right = remove(tree.element, tree.right);
		} else {
			modCount++;
			BinaryNode<T> oneChild = (tree.left != null) ? tree.left : tree.right;
			oneChild.parent = tree.parent;
			tree = oneChild;
		}
		
		return tree;
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
		if (tree == null) {
			return null;
		} else if (tree.right == null) {
			return tree;
		} else {
			return findMax(tree.right);
		}
	}
	
	private boolean contains(T x, BinaryNode<T> tree) {
		if (tree == null) {
			return false;
		}
		
		int compareResult = x.compareTo(tree.element);
		
		if (compareResult < 0) {
			return contains(x, tree.left);
		} else if (compareResult > 0) {
			return contains(x, tree.right);
		} else {
			return true;
		}
	}	
	
	public java.util.Iterator<T> iterator() {
		return new MyTreeSetIterator();
	}
	
	private class MyTreeSetIterator implements java.util.Iterator<T> {
		private BinaryNode<T> current = findMin(root);
		private BinaryNode<T> previous = current;
		private int exceptModCount = modCount;
		private boolean okToRemove = false;
		private boolean atEnd = false;

		@Override
		public boolean hasNext() {
			return atEnd;
		}

		@Override
		public T next() throws java.util.NoSuchElementException, java.util.ConcurrentModificationException{
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			} 
			if (modCount != exceptModCount) {
				throw new java.util.ConcurrentModificationException();
			}
			
			T nextItem = current.element;
			previous = current;
			
			//更新current
			if (current.right != null) {//存在右儿子，下一个节点就是右子树的最小节点
				current = findMin(current.right);
			} else {
				BinaryNode<T> child = current;
				BinaryNode<T> parent = current.parent;

				while (current !=null && current.left != child) {
					child = current;
					current = current.parent;
				}

				if (current == null) {
					atEnd = true;
				}
			}

			okToRemove = true;

			return nextItem;
		}

		public void remove() throws java.util.ConcurrentModificationException, java.lang.IllegalStateException{
			if (modCount != exceptModCount) {
				throw new java.util.ConcurrentModificationException();
			}
			if (!okToRemove) {
				throw new java.lang.IllegalStateException();
			}

			MyTreeSet.this.remove(previous.element);
			okToRemove = false;
		}
		
	}

	private static class BinaryNode<E>{
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;
		BinaryNode<E> parent;
		
		BinaryNode(E theElement) {
			this(theElement, null, null, null);
		}
		
		BinaryNode(E theElement, BinaryNode<E> leftTree, BinaryNode<E> rightTree, BinaryNode<E> parentTree) {
			element = theElement;
			left = leftTree;
			right = rightTree;
			parent = parentTree;
		}
	} 
}

class UnderFlowException extends Exception{}
