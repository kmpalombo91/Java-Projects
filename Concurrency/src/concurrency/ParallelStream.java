package concurrency;

import java.util.Arrays;

public class ParallelStream {
    public static void main(String[] args) {
        Arrays.asList(1, 2, 3, 4, 5, 6).stream().forEach(
        s -> System.out.print(s + " "));
        System.out.println();
        
        Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream().forEach(
        s -> System.out.print(s + " "));
        System.out.println();
        
        Arrays.asList("jackal", "kangaroo", "lemur").parallelStream().map(
        s -> {System.out.println(s); return s.toUpperCase();}).forEach(
        System.out::println);
    }
}
