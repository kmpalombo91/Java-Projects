package chapter3;

public class E3_5 {
	public static void printGrid(int rows, int cols) {
		int count = 1;
		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= cols; j++) {
				if (j == cols) {
				System.out.print(count + "");
				} else {
					System.out.print(count + ", ");
				}
				count += rows;
			}
			count = i + 1;
			System.out.println();
		}
	}

	public static void main(String[] args) {
		printGrid(3, 6);
		printGrid(5, 3);
	}
}
