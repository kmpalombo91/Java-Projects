package chapter4;

import java.util.Scanner;

public class E4_8 {

    public static void smallestLargest() {
        Scanner scan = new Scanner(System.in);
        System.out.print("How many numbers do you want to enter? ");
        int size = scan.nextInt();
        int[] nums = new int[size];
        int max = 0, min = 0;
        for (int i = 0; i < nums.length; i++) {
            System.out.print("Number " + (i + 1) + ": ");
            nums[i] = scan.nextInt();
            if (nums[i] > max) {
                max = nums[i];
            }
            if (nums[i] < min || min == 0) {
                min = nums[i];
            }
        }
        System.out.println("Smallest = " + min);
        System.out.println("Largest = " + max);
    }

    public static void main(String[] args) {
        smallestLargest();
    }
}
