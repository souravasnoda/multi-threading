package Synchronization;

public class Test {
    
    public static void main(String[] args) {
        Counter counter =new Counter();
        MYThread t1 = new MYThread(counter);
        MYThread t2 = new MYThread(counter);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
            //both threads are running simultaneously so at a time say value is 101 both will increment it at a time so it will become 102 but it should be 103;
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(counter.getCount());
    }
}
