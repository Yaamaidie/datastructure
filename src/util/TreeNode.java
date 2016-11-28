package util;

//树
public class TreeNode<T> {
	private T element;
	private TreeNode<T> firstChild;
	private TreeNode<T> nextSibling;
	
	public TreeNode() {
		element = null;
		firstChild = null;
		nextSibling = null;
	} 
	
	public TreeNode(T x) {
		element = x;
		firstChild = null;
		nextSibling = null;
	}
	
	public void addChild(T x, TreeNode<T> node) {
		node.nextSibling = new TreeNode<T>(x);
	}
}
