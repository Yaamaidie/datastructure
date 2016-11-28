package demo.ch4;

import util.BinaryNode;
import util.MyStack;

/**
 *	后缀表达式-->表达式树
 */
public class ExpressionTree {
	
	public static void main(String[] args) {
		ExpressionTree et = new ExpressionTree();
		Character[] token = {'a', 'b', '+', 'c', 'd', 'e', '+', '*', '*'};
		BinaryNode<Character> root = et.postfixToExpressionTree(token);
		System.out.println(root.getClass().getName());
	}
	
	private static final char[] operators = {'(', ')', '+', '-', '*', '/', '^'};
	
	public BinaryNode<Character> postfixToExpressionTree(Character[] token) {
		MyStack<BinaryNode<Character>> stack = new MyStack<>();
		for (int i = 0; i < token.length; i++) {
			if (!isOperator(token[i])) {//是操作数，构造一个单节点推入栈中
				BinaryNode<Character> node = new BinaryNode<Character>(token[i], null, null);
				stack.push(node);
			} else {//是操作符，从栈中弹出两棵树并构造一课新树
				BinaryNode<Character> left = stack.pop();
				BinaryNode<Character> right = stack.pop();
				BinaryNode<Character> newTree = new BinaryNode<Character>(token[i], left, right);
				stack.push(newTree);
			}
		}
		return stack.pop();
	}
	
	private boolean isOperator(Character c) {
		boolean isOperator = false;
		for (int i = 0; i < operators.length; i++) {
			if (c == operators[i]) {
				isOperator = true;
				break;
			}
		}
		return isOperator;
	}
	
}
