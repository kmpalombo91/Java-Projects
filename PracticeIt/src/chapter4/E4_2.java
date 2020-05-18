package chapter4;

public class E4_2 {

    public static String repl(String str, int rep) {
        String temp = "";
        for (int i = 0; i < rep; i++) {
            if (rep <= 0) {
                str = "";
            } else {
                temp += str;
            }
        }
        return temp;
    }

    public static void main(String[] args) {
        System.out.println(repl("hello", 3));
    }
}
