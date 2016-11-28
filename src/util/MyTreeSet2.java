package util;

/**
 * 习题4.13 节点保留下一个最小和最大节点的链，用于双向遍历。
 * @author lee
 *
 * @param <T>
 */
public class MyTreeSet2<T extends Comparable<? super T>> implements Iterable<T>{
	public static void main(String[] args) {
		MyTreeSet2<Integer> treeSet = new MyTreeSet2<>();
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
		root = insert(x, root, null, null);
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
	
	private BinaryNode<T> insert(T x, BinaryNode<T> tree, BinaryNode<T> prev, BinaryNode<T> next) {
		if (tree == null) {
			modCount++;
			BinaryNode<T> newNode = new BinaryNode<T>(x, null, null, next, prev);
			
			if (next != null) {
				next.prev = newNode;
			}
			if (prev != null) {
				prev.next = newNode;
			}
			return newNode;
		}
		
		int compareResult = x.compareTo(tree.element);
		
		if (compareResult < 0) {
			tree.left = insert(x, tree.left, tree, prev);//tree有左儿子，则tree是左儿子的下一个最大节点；
		} else if (compareResult > 0){
			tree.right = insert(x, tree.right, next, tree);//tree有右儿子，则tree是右儿子是下一个最小节点
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
			tree.prev.next = tree.next;//更新next链
			tree.next.prev = tree.prev;//更新prev链
			tree = (tree.left != null) ? tree.left : tree.right;
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
			previous = current;
			//更新current

			current = current.next;
			
				if (current == null) {
					atEnd = true;
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
			BinaryNode<E> next;//下一个最小节点
			BinaryNode<E> prev;//下一个最大节点
			
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