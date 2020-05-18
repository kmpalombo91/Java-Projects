package concurrency;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Concurrency {

    public static void main(String[] args) {
        //Create tasks to run
        Runnable printA = new PrintChar('a', 100);
        Runnable printB = new PrintChar('b', 100);
        Runnable print100 = new PrintNum(100);

        //Create threads
        Thread thread1 = new Thread(printA);
        Thread thread2 = new Thread(printB);
        Thread thread3 = new Thread(print100);

        //Start threads
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class PrintChar implements Runnable {

    private char charToPrint;
    private int times;

    public PrintChar(char c, int t) {
        charToPrint = c;
        times = t;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            System.out.print(charToPrint);
        }
    }
}

class PrintNum implements Runnable {

    private int lastNum;

    public PrintNum(int n) {
        lastNum = n;
    }

    public void run1() {
        try {
            for (int i = 1; i <= lastNum; i++) {
                System.out.print(" " + i);
                if (i < 50) {
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException ex) {
        }
    }

    @Override
    public void run() {
        Thread thread4 = new Thread(new PrintChar('c', 200));
        thread4.start();
        try {
            for (int i = 1; i <= lastNum; i++) {
                System.out.print(" " + i);
                if (i == 50) {
                    thread4.join();
                }
            }
        } catch (InterruptedException e) {
        }
    }
}
