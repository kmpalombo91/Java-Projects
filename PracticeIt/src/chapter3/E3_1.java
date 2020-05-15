package chapter3;

public class E3_1 {
	public static void printNumbers(int max) {
		for (int i = 1; i <= max; i++) {
			System.out.print("[" + i + "] ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		printNumbers(15);
		printNumbers(5);
	}
}
