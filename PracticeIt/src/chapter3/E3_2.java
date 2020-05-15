package chapter3;

public class E3_2 {
	public static void printPowersOf2(int max) {
		for (int i = 0; i <= max; i++) {
			System.out.print((int)Math.pow(2, i) + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		printPowersOf2(3);
		printPowersOf2(10);
	}
}
