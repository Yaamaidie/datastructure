package util;

/**
 * 二叉树
 */
public class BinaryNode<T> {
	private T element;
	BinaryNode<T> left;
	BinaryNode<T> right;
	
	public BinaryNode() {
		this(null, null, null);
	}
	
	public BinaryNode(T x) {
		this(x, null, null);
	}
	
	public BinaryNode(T x, BinaryNode<T> leftBranch, BinaryNode<T> rightBranch) {
		left = leftBranch;
		right = rightBranch;
		element = x;
	}
	
	public T getElement() {
		return element;
	}
	
	public BinaryNode<T> leftBranch() {
		return left;
	}
	
	public BinaryNode<T> rightBranch() {
		return right;
	}
}
