package chapter3;

import java.util.Scanner;

public class E3_20 {
	public static void inputBirthday(Scanner scan) {
		int month = 0;
		String mon = "";
		int year = 0;
		System.out.print("On what day of the month were you born? ");
		month = scan.nextInt();
		System.out.print("What is the name of the month in which you were born? ");
		mon = scan.next();
		System.out.print("During what year were you born? ");
		year = scan.nextInt();
		System.out.println("You were born on " + mon + " " + month + ", " + year + ". You're mighty old!");
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		inputBirthday(scan);
	}
}
