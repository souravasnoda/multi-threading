package Executor_framework;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Scheduled_Executor_service {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler =  Executors.newScheduledThreadPool(1);
        scheduler.schedule(()->{
            System.out.println("Task is running after 1 sec!");
        }, 1, TimeUnit.SECONDS);

        scheduler.scheduleAtFixedRate(
            ()->System.out.println("Task will run after every 5 sec")
            , 2, 5, TimeUnit.SECONDS);
        scheduler.schedule(()->{System.out.println("Initializing shutdown...");scheduler.shutdown();}, 20, TimeUnit.SECONDS);
        scheduler.schedule(()->{
            scheduler.shutdown();
        }, 50, TimeUnit.SECONDS);
        ScheduledFuture<?> scheduledFuture = scheduler.scheduleWithFixedDelay(()->System.out.println("Task execute after 3 sec"), 3, 3, TimeUnit.SECONDS);

        Executors.newCachedThreadPool();// create threadpool and create new threads and terminate after 60 sec if they are not in use and dynamically set thread pool but con is we cant manage number ofthreads could be create
        //good for short live and low variable tasks 
    }
    
    
}
