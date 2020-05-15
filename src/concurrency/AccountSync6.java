package concurrency;

public class AccountSync6 {

    private Account account = new Account();

    public static void main(String[] args) {
        //ExecutorService executor = Executors.newFixedThreadPool(2);
        AccountSync6 a5 = new AccountSync6();
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

        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        public void withdraw(int amount) {
            synchronized (account) {
                try {
                    while (balance < amount) {
                        System.out.println("\t\t\tWait for a deposit");
                        account.wait();
                    }
                    balance -= amount;
                    System.out.println("\t\t\tWithdraw " + amount + "\t\t" + getBalance());
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } finally {}
            }
        }

        public void deposit(int amount) {

            synchronized (account) {
                try {
                    balance += amount;
                    System.out.println("Deposit " + amount + "\t\t\t\t\t" + getBalance());
                    // This delay is deliberately added to magnify the
                    // data-corruption problem and make it easy to see.
                    account.notifyAll();
                } finally {}
            }
        }
    }
}
