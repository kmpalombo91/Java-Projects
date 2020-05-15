package concurrency;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountSync5 {

    private Account account = new Account();

    public static void main(String[] args) {
        //ExecutorService executor = Executors.newFixedThreadPool(2);
        AccountSync5 a5 = new AccountSync5();
        Thread t1 = new Thread(a5.new DepositTask());
        Thread t2 = new Thread(a5.new WithdrawTask());
        t1.start();
        t2.start();
        System.out.println("Thread 1\t\tThread 2\t\tBalance");
    }

    public class DepositTask implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    account.deposit((int) (Math.random() * 10) + 1);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public class WithdrawTask implements Runnable {

        @Override
        public void run() {
            while (true) {
                account.withdraw((int) (Math.random() * 10) + 1);
            }
        }  
    }

    // An inner class for account
    private class Account {

        private Lock lock = new ReentrantLock(); // used as explicit lock
        private Condition newDepositCondition = lock.newCondition();
        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        public void withdraw(int amount) {
            lock.lock();
            try {
                while (balance < amount) {
                    System.out.println("\t\t\tWait for a deposit");
                    newDepositCondition.await();
                }
                balance -= amount;
                System.out.println("\t\t\tWithdraw " + amount + "\t\t" + getBalance());

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void deposit(int amount) {
            lock.lock(); // Acquire the lock
            try {
                balance += amount;
                System.out.println("Deposit " + amount + "\t\t\t\t\t" + getBalance());
                // This delay is deliberately added to magnify the
                // data-corruption problem and make it easy to see.
                newDepositCondition.signalAll();
            } finally {
                lock.unlock(); // Release the lock
            }
        }
    }
}
