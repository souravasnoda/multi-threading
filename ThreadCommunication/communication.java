package ThreadCommunication;
//earlier problem:-
//If there is no communication between thread their will cpu wastage because Threads will check again and again for Their turn
//Eg;- in case when their is producer who produce data and consumer who consumes so consumer have to check again and again if data comes or not if there is no thread communication
//but what if producer told consumer when data comes;
public class communication {
    public static void main(String[] args) {
        SharedResource resource =new SharedResource();
        Thread t1 = new Thread(new Producer(resource),"t1");
        Thread t2 = new Thread(new Consumer(resource),"t1");
        t1.start();
        t2.start();
    }
}
class SharedResource{
    private int data;
    private boolean hasData;

    public synchronized void produce(int value){
        while(hasData){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        data =value;
        hasData =true;
        System.out.println("Produce: "+value);
        notify();
    }
    
    public synchronized int consume(){
        while (!hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } 
        }
        hasData =false;
        System.out.println("Consumed: " +data );
        notify();//notify will tell not to wait
        return data;
    }
}

class Producer implements Runnable{

    private SharedResource resource;
    public Producer(SharedResource resource){
        this.resource=resource;
    }
    @Override
    public void run(){
       for(int i=0;i<10;i++) {
        resource.produce(i);
        
       }
    }
}

class Consumer implements Runnable{

    private SharedResource resource;

    public Consumer(SharedResource resource){
        this.resource=resource;
    }
    @Override
    public void run(){
        for(int i=0;i<10;i++){
            int value =resource.consume();
            
        }
    }
}
//How its working?
//We are applying locks onobject of shared resource
// 1. SharedResource (Common Object for Producer and Consumer)
// It has two things:

// data → the actual value to produce and consume.

// hasData → a flag (true/false) that tells whether data is available or not.

// 2. produce() method (Producer Thread calls this)
// It is marked synchronized, so only one thread can run inside it at a time.

// If hasData == true (means old data is still there, not yet consumed), the producer waits.

// When consumer consumes and notifies, producer wakes up, stores the new data (data = value), sets hasData = true, and notifies consumer.

// ✅ So, producer says:
// "If previous data is still there, I will wait."
// "Once consumed, I will produce new data and wake the consumer!"

// 3. consume() method (Consumer Thread calls this)
// It is also synchronized.

// If hasData == false (no data yet produced), the consumer waits.

// When producer produces and notifies, consumer wakes up, reads the data, sets hasData = false, and notifies producer.

// ✅ So, consumer says:
// "If no data is there, I will wait."
// "Once produced, I will consume it and wake the producer!"

// 4. Producer Thread (Producer class)
// It keeps producing 0 to 9 numbers.

// For each number:

// Calls resource.produce(i).

// 5. Consumer Thread (Consumer class)
// It keeps consuming 10 numbers.

// For each consumption:

// Calls resource.consume().

// 6. wait() and notify()
// wait() → thread goes into waiting state and releases the lock.

// notify() → wakes up a waiting thread (producer or consumer), gives it chance to acquire lock and continue.

// 🔥 Final Flow
// ✅ Producer thread starts → Checks if previous data is consumed → If yes, produces data → Notifies consumer.
// ✅ Consumer thread starts → Checks if data is available → If yes, consumes it → Notifies producer.

// ✅ They communicate nicely without wasting CPU cycles.

// 🔥 Important Small Points (for Interview)
// SharedResource object is common between producer and consumer.

// synchronized ensures mutual exclusion (only one thread at a time).

// wait() is called inside while loop, to recheck condition when waking up (good practice).

// notify() wakes up the other thread (either producer or consumer).

// CPU is not wasted by busy checking.

