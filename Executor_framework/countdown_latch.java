package Executor_framework;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class countdown_latch {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        int numberOfServices =3;
        CountDownLatch latch = new CountDownLatch(numberOfServices);
        executorService.submit(new DependentService(latch));
        executorService.submit(new DependentService(latch));
        executorService.submit(new DependentService(latch));
        // Future<String> f1 = executorService.submit(new DependentService());
        // Future<String> f2 = executorService.submit(new DependentService());
        // Future<String> f3 = executorService.submit(new DependentService());
        // f1.get();//wait to finish it
        // f2.get();
        // f3.get();
        // System.out.println("All dependent service finished. Starting main Service..");//Assume this is our main work and to perform this we need all above 3 executorService to start and wait till all these three service to get completed so we will use countDown latch hence so above is better way
        latch.await();
        System.out.println("Main");
        executorService.shutdown();


    }

    
}
class DependentService implements Callable<String> {
    private final CountDownLatch latch;
    public  DependentService(CountDownLatch latch){
        this.latch=latch;
    }

    @Override
    public String call() throws Exception {
         
        
         try {
            System.out.println(Thread.currentThread().getName()+" running..");   
            Thread.sleep(1000);
         } finally  {
            latch.countDown();
         }
         return "OK";
    }

    
}