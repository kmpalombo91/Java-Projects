package chapter3;

public class E3_8 {
	public static void quadratic(double a, double b, double c) {
		double root1 = (-b + (Math.sqrt(Math.pow(b, 2) - (4 * a * c)))) / (2 * a);
		double root2 = (-b - (Math.sqrt(Math.pow(b, 2) - (4 * a * c)))) / (2 * a);
		System.out.println("First root = " + root1);
		System.out.println("Second root = " + root2);
	}

	public static void main(String[] args) {
		quadratic(1, -7, 12);
		quadratic(1, 3, 2);
	}
}
