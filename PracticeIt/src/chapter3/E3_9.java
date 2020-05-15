package chapter3;

public class E3_9 {
	public static int lastDigit(int num) {
		return Math.abs(num % 10);
	}

	public static void main(String[] args) {
		System.out.println(lastDigit(3572));
		System.out.println(lastDigit(-947));
		System.out.println(lastDigit(6));
		System.out.println(lastDigit(35));
		System.out.println(lastDigit(123456));
	}
}
