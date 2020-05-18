package concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibonacciParallel {

    public static void main(String[] args) {
        System.out.println(fibParallel(9));
    }

    public static int fibParallel(int n) {
        RecursiveTask<Integer> task = new FibonacciParallel.FibTask(n);
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(task);
    }

    private static class FibTask extends RecursiveTask<Integer> {

        int fib;

        public FibTask(int fib) {
            this.fib = fib;
        }

        @Override
        protected Integer compute() {
            if (fib < 20) {
                int f1 = 1;
                int f2 = 1;
                int sum = f1 + f2;

                for (int i = 3; i < fib; i++) {
                    f1 = f2;
                    f2 = sum;
                    sum = f1 + f2;
                }
                return sum;
            } else {
                RecursiveTask<Integer> left = new FibonacciParallel.FibTask(fib - 1);
                RecursiveTask<Integer> right = new FibonacciParallel.FibTask(fib - 2);
                right.fork();
                left.fork();

                return new Integer(left.join().intValue() + right.join().intValue());
            }
        }
    }
}
