package chapter3;

public class E3_17 {
	public static String padString(String str, int len) {
		String pad = "";
		if (str.length() >= len) {
		return str;
		} else {
			for (int i = 0; i < len - str.length(); i++) {
				pad += " ";
			}
			return pad + str;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(padString("hello", 8));
		System.out.println(padString("congratulations", 10));
	}
}
