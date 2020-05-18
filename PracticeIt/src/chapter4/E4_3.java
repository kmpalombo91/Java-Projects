package chapter4;

public class E4_3 {
    public static String season(int month, int day) {
        if ((month < 3) || (month == 12 && day >= 16) || (month == 3 && day <= 15)) {
            return "Winter";
        } else if ((month > 3 && month < 6) || (month == 3 && day >= 16) || (month == 6 && day <= 15)) {
            return "Spring";
        } else if ((month > 6 && month < 9) || (month == 6 && day >= 16) || (month == 9 && day <= 15)) {
            return "Summer";
        } else {
            return "Fall";
        }
    }
    
    public static void main(String[] args) {
        System.out.println(season(9, 17));
    }
}
