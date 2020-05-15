package chapter3;

public class E3_4 {
	public static void printSquare(int min, int max) {
		for (int i = min; i <= max; i++) {
			for (int j = i; j <= max; j++) {
				System.out.print(j);
			}
			for (int j = min; j < i; j++) {
				System.out.print(j);
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		printSquare(1, 5);
		printSquare(3, 9);
		printSquare(0, 3);
		printSquare(5, 5);
	}
}
