package chapter4;

public class E4_5 {
    public static int pow(int base, int exponent) {
        int i = 0;
        int product = 1;
        while (i < exponent) {
            product *= base;
            i++;
        }
        return product;
    }
    
    public static void main(String[] args) {
        System.out.println(pow(3, 4));
    }
}
