public class MyThread extends Thread {
    @Override
    public void run(){
System.out.println("Running state");
try {
    Thread.sleep(200);
} catch (Exception e) {
    System.out.println(e);
}

    }
    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread();
        System.out.println(t1.getState());
        t1.start();
        System.out.println(t1.getState());//get state is method which returns State type and State is enum which have new,runnable,blocked,waiting,timed_wating and terminated
        Thread.sleep(100);
        System.out.println(t1.getState());
        t1.join();//main method waiting for t1 to get finish 
        System.out.println(t1.getState());
    }
}
