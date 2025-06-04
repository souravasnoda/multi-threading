package Locks;

import java.util.concurrent.locks.ReentrantLock;

public class UnfairLock {
    private final ReentrantLock unfairLock = new ReentrantLock();

    public  void accessResource(){
        unfairLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+": acquired the lock");
            Thread.sleep(2000);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }finally{
            System.out.println(Thread.currentThread().getName()+": Released the lock");
            unfairLock.unlock();
           
        }
    }
    public static void main(String[] args) {
        UnfairLock example = new UnfairLock();
        Runnable task = new Runnable() {
            @Override
            public void run(){
                example.accessResource();
            }
        };
        Thread t1 = new Thread(task,"Thread 1");
        Thread t2 = new Thread(task,"Thread 2");
        Thread t3 = new Thread(task,"Thread 3");
        t1.start();
        t2.start();
        t3.start();
    }
}
