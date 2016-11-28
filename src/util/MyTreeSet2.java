package util;

/**
 * 习题4.13 节点保留下一个最小和最大节点的链，用于双向遍历。
 * @author lee
 *
 * @param <T>
 */
public class MyTreeSet2<T extends Comparable<? super T>> {
	private BinaryNode<T> root;
	private int modCount;
	
	public MyTreeSet2() {
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
		return new MyTreeSet2Iterator();
	}
	
	private class MyTreeSet2Iterator implements java.util.Iterator<T> {
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

			MyTreeSet2.this.remove(previous.element);
			okToRemove = false;
		}
		
	}

	private static class BinaryNode<E>{
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;
		BinaryNode<E> next;
		BinaryNode<E> prev;
		
		BinaryNode(E theElement) {
			this(theElement, null, null, null, null);
		}
		
		BinaryNode(E theElement, 
				BinaryNode<E> lt, BinaryNode<E> rt, 
				BinaryNode<E> nt, BinaryNode<E> pv) {
			element = theElement;
			left = lt;
			right = rt;
			next = nt;
			prev = pv;
		}
	}
}
