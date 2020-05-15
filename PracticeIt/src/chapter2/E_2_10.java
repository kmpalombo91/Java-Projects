package chapter2;

public class E_2_10 {
	public static void main(String[] args) {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j <= 9; j++) {
				if (j == 9) {
			System.out.print("|");
				} else {
					System.out.print(" ");
				}
			}
		}
		System.out.println();
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j <= 9; j++) {
				if (j == 0 && i == 0) {
			System.out.print("");
				} else {
					System.out.print(j);
				}
			}
		}
		System.out.print(0);
	}
}
