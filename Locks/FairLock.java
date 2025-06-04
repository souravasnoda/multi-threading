package Locks;



import java.util.concurrent.locks.ReentrantLock;

public class FairLock {
    private final ReentrantLock fairLock = new ReentrantLock(true);
    //determine order to get lock for thread first come first serve and every thread will get lock
    public  void accessResource(){
        fairLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+": acquired the lock");
            Thread.sleep(1000);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }finally{
            System.out.println(Thread.currentThread().getName()+": Released the lock");
            fairLock.unlock();
           
        }
    }
    public static void main(String[] args) {
        FairLock example = new FairLock();
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
 
