package Synchronization;

public class MYThread extends Thread{
    private Counter counter;
    public MYThread(Counter counter){
        this.counter =counter;
    }
    @Override
    public void run(){
        for(int i=0;i<1000;i++){
            counter.increment();
        }
    }
}
