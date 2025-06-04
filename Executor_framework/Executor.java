package Executor_framework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

//Introduced in java 5 as part of java.util.concurrent package
//Simplifies the developement of concurrent applications by abstracting away many complexities involved in creating and mapping threads
//We dont do manual work to (Overhead of) create,  manage and destroy threads/
//Scalability
//Thread resuse
//Error Handling

public class Executor {
    // if we do like this it will take time because it synchronized
    // public static void main(String[] args) {
    // long startTime = System.currentTimeMillis();
    // for(int i=1;i<10;i++){
    // System.out.println(factorial(i));
    // }
    // System.out.println("total time: "+(System.currentTimeMillis()-startTime));
    // }

    public static void main(String[] args) {
        // This basic multithreading
        // long startTime = System.currentTimeMillis();
        // Thread[] threads = new Thread[9];
        // for(int i=1;i<10;i++){
        // int finalI =i;//because thread will take changed value when it get intialized
        // later
        // threads[i-1] = new Thread(
        // ()->{
        // long res = factorial(finalI);
        // System.out.println(res);
        // }
        // );

        // //we can do like this because it still work as blocking
        // // try {
        // // thread.join();
        // // } catch (InterruptedException e) {
        // // // TODO Auto-generated catch block
        // // e.printStackTrace();
        // // }
        // threads[i-1].start();
        // }
        // for(Thread thread:threads){
        // try {
        // thread.join();
        // } catch (Exception e) {
        // // TODO: handle exception
        // }
        // }
        // System.out.println("total time: "+(System.currentTimeMillis()-startTime));

        // now we will use executor

        long startTime = System.currentTimeMillis();
        ExecutorService executorsService = Executors.newFixedThreadPool(3);//we can use Executor instead of ExecutorService but have limited methods and no shutdown method and execute instead of submit
        //execute will return Void
        //submit() will return Future<?>

        for (int i = 1; i < 10; i++) {
            int finalI = i;// because thread will take changed value when it get intialized later
            Future<?> future=executorsService.submit(
                //we can use future to play with executorService instance we can use multiple methods 
                    () -> {
                        long res = factorial(finalI);
                        System.out.println(res);
                    });

            // we can do like this because it still work as blocking
            // try {
            // thread.join();
            // } catch (InterruptedException e) {
            // // TODO Auto-generated catch block
            // e.printStackTrace();
            // }

        }
        executorsService.shutdown();// we cant use executorService again after this
        try {
            while (!executorsService.awaitTermination(1, TimeUnit.SECONDS)) {//it will return true or false it is done it will return true hence it will comes out of loop it's not ended yet it will return false and print Waiting...
                System.out.println("Waiting...");
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("total time: " + (System.currentTimeMillis() - startTime));

    }

    private static long factorial(int n) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            // TODO: handle exception
        }
        long res = 1;
        for (int i = 1; i < n; i++) {
            res *= i;
        }
        return res;
    }
}
