package demo.ch2;

import java.util.Collection;

public class Shape {
	private double area;
	
	public double area() {
		return area;
	}
	
//	public static double totalArea(Collection<Shape> arr) {//集合不是协变的，所以Collection<Circle>不能作为参数
//		double total = 0;
//		for (Shape s : arr) {
//			if (s != null) {
//				total += s.area();
//			}
//		}
//		return total;
//	}
	
	public static double totalArea(Collection<? extends Shape> arr) {//使用通配符，则Collection<Circle>可以作为参数
		double total = 0;
		for (Shape s : arr) {
			if (s != null) {
				total += s.area();
			}
		}
		return total;
	}
	
}
