public class Yield extends Thread{

    public  Yield(String name){
        super(name);
    }
    @Override
    public void run(){
        for(int i=0;i<150;i++){
            System.out.println(Thread.currentThread().getName()+" is running");;
            Thread.yield();//give chance to other thread also to run;
        }
    }
    //main thread
    public static void main(String[] args) {
        Yield t1 = new Yield("t1");//user thread
        Yield t2 = new Yield("t2");
        t1.setDaemon(true);//we are setting it as demon it will automatically stop when other thread are set after their work is done
        t1.start();
        t2.start();
        //Demon thread works on backgroud JVM will not wait for it , JVM end when main and user thread  work is done
    }
}
