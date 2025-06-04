public class ThreadMethods extends Thread 
{   //First
    // @Override
    // public void run(){
    //     System.out.println("Thread is running");
    //     for(int i=0;i<5;i++){
    //         try {
    //             Thread.sleep(5000);
    //         } catch (Exception e) {
    //             // TODO: handle exception
    //         }
    //         System.out.println(i);
    //     }
    // }
    public ThreadMethods(String name){
        super(name);
    }
    @Override
    public void run(){
        for(int i=0;i<5;i++){
            System.out.println(currentThread().getName()+" - "+"priority: "+currentThread().getPriority()+" count: "+i);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ThreadMethods l =new ThreadMethods("low");
        ThreadMethods m =new ThreadMethods("mid");
        ThreadMethods h =new ThreadMethods("high");
        //first
        // t1.start();
        // t1.join();
        // System.out.println("hello");
        l.setPriority(MIN_PRIORITY);
        m.setPriority(NORM_PRIORITY);
        h.setPriority(MAX_PRIORITY);
        //no strict rule for jvm it just give hints to finish high priority task
        l.start();
        m.start();
        h.start();
    }
}
