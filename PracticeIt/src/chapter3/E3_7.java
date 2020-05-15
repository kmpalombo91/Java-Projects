package chapter3;

public class E3_7 {
	public static int largestAbsVal(int num1, int num2, int num3) {
		int max1 = Math.max(Math.abs(num1), Math.abs(num2));
		return Math.max(max1, Math.abs(num3));
	}

	public static void main(String[] args) {
		System.out.println(largestAbsVal(7, -2, -11));
		System.out.println(largestAbsVal(-4, 5, 2));
	}
}
