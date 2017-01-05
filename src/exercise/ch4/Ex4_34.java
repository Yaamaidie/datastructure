package exercise.ch4;

/**
 * 生成一棵随机二叉树
 * @author lee
 *
 */
public class Ex4_34 {
	
	public static void main(String[] args) {
		Node t = makeRandomTree(20);
		printTree(t);
	}
	
	static Node makeRandomTree(int n) {
		return makeRandomTree(1, n);
	}
	
	static void printTree(Node t) {
		if (t != null) {
			printTree(t.left);
			System.out.print(t.element);
			printTree(t.right);
		}
	}
	
	private static Node makeRandomTree(int lower, int upper) {
		Node t;
		int x;
		
		if (lower <= upper) {
			t = new Node(x = randInt(lower, upper), 
					makeRandomTree(lower, x - 1), 
					makeRandomTree(x +1, upper));
		} else {
			t = null;
		}
		
		return t;
	}
	
	private static int randInt(int lower, int upper) {
		return (int)((upper - lower) * Math.random()) + lower;
	}
	
	private static class Node {
		int element;
		Node left;
		Node right;
		
		Node(int e, Node l, Node r) {
			element = e;
			left = l;
			right = r;
		}
	}
	
}
