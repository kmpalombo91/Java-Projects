package concurrency;

import java.util.concurrent.*;

public class AccountSemaphore {

    private static Account account = new Account();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        // Create and launch 100 threads
        for (int i = 0; i < 100; i++) {
            executor.execute(new AddAPennyTask());
        }
        executor.shutdown();
        // Wait until all tasks are finished
        while (!executor.isTerminated()) {
        }
        System.out.println("What is balance? " + account.getBalance());
    }

    // A thread for adding a penny to the account
    private static class AddAPennyTask implements Runnable {

        public void run() {
            account.deposit(1);
        }
    }

    // An inner class for account
    private static class Account {

        private static Semaphore semaphore = new Semaphore(1); // synchronizes threads, like lock and synchronized
        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        public void deposit(int amount) {
            try {
                semaphore.acquire();
                int newBalance = balance + amount;
                Thread.sleep(5);
                balance = newBalance;
            } catch (InterruptedException ex) {
            } finally {
                semaphore.release();
            }
        }
    }
}
