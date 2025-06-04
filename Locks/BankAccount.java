package Locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int balance = 100;

    // public synchronized void withdraw(int amount){
    //     System.out.println(Thread.currentThread().getName()+" attempting to withdraw "+amount);
    //     if(balance>=amount){
    //         try {
    //             Thread.sleep(3000);
    //         } catch (Exception e) {
    //             // TODO: handle exception
    //         }
    //         balance-=amount;
    //         System.out.println("Completed withdrawal and remaining balance is: "+balance);
    //     }else{
    //         System.out.println("Insufficient Balance");
    //     }
    // }
    //Using Lock
    private final ReentrantLock lock = new ReentrantLock();//check video at1:35 to understand reentrant lock
    public  void withdraw(int amount){
        System.out.println(Thread.currentThread().getName()+" attempting to withdraw "+amount);
        try {
            //lock.lock() same as synchronized it will wait untill lock will get free
            if(lock.tryLock(1000,TimeUnit.MILLISECONDS)){
                if(balance>=amount){
                            try {
                                Thread.sleep(1000);
                                balance-=amount;
                                System.out.println("Completed withdrawal and remaining balance is: "+balance);
                        
                            } catch (Exception e) {
                                Thread.currentThread().interrupt();//so that if we get interrupt exception we can perform any action on that thread else we will lost it state
                            }finally{
                                lock.unlock();
                            }
                            
            }else{
                System.out.println("Insufficient Balance");
            }
        } else{
            System.out.println(Thread.currentThread().getName()+" could not acquire lock, will try later");
        }
    }catch (Exception e) {
            // TODO: handle exception
        }
    }
    
}
