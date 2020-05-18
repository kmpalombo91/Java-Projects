package chapter4;

public class E4_6 {

    public static void printRange(int num1, int num2) {
        if (num1 <= num2) {
            while (num1 <= num2) {
                System.out.print(num1 + " ");
                num1++;
            }
        } else if (num2 <= num1) {
            while (num2 <= num1) {
                System.out.print(num1 + " ");
                num1--;
            }
        } else {
            System.out.print(num1);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printRange(2, 7);
        printRange(19, 11);
    }
}
