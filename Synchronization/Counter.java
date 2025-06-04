package Synchronization;
//It have locking system with intrinsic lock on it.
//to avoid race condition and make sure mutual exclusion and add intrinsic lock on it.
public class Counter {

    private int count =0;
    //no need to synchronize whole block we can synchronize only one block
    // public synchronized void increment(){
    //     count++;
    // }
    public  void increment(){
        synchronized(this){
            count++;
        }
        
    }
    public int getCount(){
        return count;
    }

    
}
