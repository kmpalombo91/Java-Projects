package chapter3;

public class E3_3 {
	public static void printPowersOfN(int base, int exponent) {
		for (int i = 0; i <= exponent; i++) {
			System.out.print((int)Math.pow(base, i) + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		printPowersOfN(4, 3);
		printPowersOfN(5, 6);
		printPowersOfN(-2, 8);
	}
}
