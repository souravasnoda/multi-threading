public class InterruptMethod extends Thread{
    @Override
    public void run(){
        try {
            Thread.sleep(200);
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    public static void main(String[] args) {
        InterruptMethod t1 =new InterruptMethod();
        t1.start();
        t1.interrupt();
    }
}
