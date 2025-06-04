package Lambda;

public class LambdaExpression {
    public static void main(String[] args) {
        // Runnable (a functional interface so we can use lambda expression) runnable =new Runnable() {
    //     @Override
    //     public void run(){
    //         System.out.println("hello");
    //     }
    // };
    Runnable runnable =()->{
        System.out.println("Hello");
    };
    
    Thread thread = new Thread(runnable);
    Thread t3 = new Thread(()->{
        System.out.println("World");
    });
    Thread t2 = new Thread(()->{
        for(int i =0;i<5;i++){
            System.out.println(i);
        }
    });
    thread.start();
    t2.start();
    t3.start();
    }
}
