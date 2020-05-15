package chapter3;

public class E3_18 {
	public static void vertical(String str) {
		for (int i = 0; i < str.length(); i++) {
			System.out.println(str.charAt(i));
		}
	}
	
	public static void main(String[] args) {
		vertical("hey now");
	}
}
