package chapter4;

import java.util.Scanner;

public class E4_11 {

    public static void longestName(Scanner scan, int n) {
        String[] names = new String[n];
        String biggest = "";
        boolean b = false;
        String tie = "";
        for (int i = 0; i < names.length; i++) {
            System.out.print("name #" + (i + 1) + "? ");
            names[i] = scan.next();
            if (biggest.length() < names[i].length()) {
                biggest = names[i];
            }
        }
        int j = 0;
        while (j < n) {
            if (biggest.length() == names[j].length() && !biggest.equals(names[j])) {
                b = true;
                tie = "(There was a tie!)";
            }
            j++;
        }
        System.out.println(Character.toUpperCase(biggest.charAt(0)) + "" + biggest.substring(1, biggest.length()).toLowerCase() + "'s name is longest");
        if (b == true) {
            System.out.println(tie);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        longestName(scan, 5);
    }
}
