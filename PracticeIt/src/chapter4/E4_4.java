package chapter4;

public class E4_4 {

    public static int daysInMonth(int month) {
        int days = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            case 2:
                days = 28;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
                break;
        }
        return days;
    }

    public static void main(String[] args) {
        System.out.println(daysInMonth(9));
    }
}
