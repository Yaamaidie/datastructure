package exercise.ch3;
/**
 * 对于递归的菲波拉契数列的运行时间分析，当N=50时，栈空间会用完吗？为什么？
 * 
 * 不会，对于树形积累，空间需求基本满足树的深度，该值是正比于输入N的
 * @author lee
 *
 */
public class Ex3_27 {
	static int fib(int n) {
		System.out.print(n + ",");
		if (n == 0 || n == 1) {
			return n;
		} else {
			return fib(n - 1) + fib(n - 2);
		}
	}
	public static void main(String[] args) {
		fib(50);
	}
}
