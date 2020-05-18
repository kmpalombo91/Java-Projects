package chapter4;

public class E4_7 {

    public static void xo(int size) {
        int front = 0;
        int back = size - 1;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (j == front || j == back) {
                    System.out.print("x");
                } else {
                    System.out.print("o");
                }
            }
            System.out.println();
            front++;
            back--;
        }
    }

    public static void main(String[] args) {
        xo(5);
        xo(8);
    }
}
