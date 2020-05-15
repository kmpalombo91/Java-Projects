package chapter3;

public class E3_6 {
	public static int largerAbsVal(int num1, int num2) {
		return Math.max(Math.abs(num1), Math.abs(num2));
	}

	public static void main(String[] args) {
		System.out.println(largerAbsVal(11, 2));
		System.out.println(largerAbsVal(4, -5));
	}
}
