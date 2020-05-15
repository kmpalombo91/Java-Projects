package chapter3;

public class E3_19 {
	public static void reverse(String str) {
		for (int i = str.length() - 1; i >= 0; i--) {
			System.out.print(str.charAt(i));
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		reverse("hey now");
		reverse("hello there!");
	}
}
