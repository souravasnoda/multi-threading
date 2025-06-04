package Locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
//remove unnecessary locking if multiple threads are reading and none is writing
public class ReadWriteLocking {
    private int count =0;
    private final ReadWriteLock lock = new ReentrantReadWriteLock(true);
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public void increment(){
        writeLock.lock();
        try {
            count++;
            Thread.sleep(50);
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            writeLock.unlock();
        }
    }
    public int getCount(){
        readLock.lock();//multiple thread can acquire this lock if writelock is not acquire by any thread not in case of other lock or synchronization
        try {
            return count;
        } finally{
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLocking readWriteLocking =new ReadWriteLocking();
        Runnable readtask =new Runnable() {
            @Override
            public void run(){
                for(int i=0;i<5;i++){
                    System.out.println(Thread.currentThread().getName()+" read: "+readWriteLocking.getCount());
                }
            }
        };

        Runnable writetask =new Runnable() {
            @Override
            public void run(){
                for(int i =0;i<6;i++){
                   readWriteLocking.increment();
                   System.out.println(Thread.currentThread().getName()+" incremented ");
                }
            }
        };
        Thread read1 = new Thread(readtask,"read1: ");
        Thread read2 = new Thread(readtask,"read2: ");
        Thread write1 = new Thread(writetask,"write1: ");
        Thread write2 = new Thread(writetask,"write2: ");
        read1.start();
        
        write1.start();
        read2.start();
        // write2.start();
    }

    
}
