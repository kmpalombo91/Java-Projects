package chapter4;

import java.util.Scanner;

public class E4_9 {

    public static void printGPA() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a student record: ");
        String name = scan.nextLine();
        int size = scan.nextInt();
        double sum = 0;
        int[] grades = new int[size];
        for (int i = 0; i < grades.length; i++) {
            grades[i] = scan.nextInt();
            sum += grades[i];
        }
        System.out.println(name + "'s grade is " + (sum / size));
    }

    public static void main(String[] args) {
        printGPA();
    }
}
