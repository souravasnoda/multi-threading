package Executor_framework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPooling {
    //COllection of pre - intialized thread ready to perform task;
    //Resource management -> creating and destroy thread
    //Increase response time because thread is already ready;
    //control over thread count -> how much max number of threads we can create 
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService =Executors.newFixedThreadPool(3);
    Future<Integer> future = executorService.submit( ()->12+2);
    Integer i=0;
    try {
        i = future.get();
    } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (ExecutionException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    
    Callable<Integer> c1 =()->{
        System.out.println("Task 1");
        return 1;
    };
    Callable<Integer> c2 =()->{
        System.out.println("Task 2");
        return 2;
    };;
    Callable<Integer> c3 =()->{
        System.out.println("Task 3");
        return 3;
    };;
    List<Callable<Integer>> list = Arrays.asList(c1,c2,c3);
    List<Future<Integer>> futures=new ArrayList<>();
    
    futures=  executorService.invokeAll(list);//stop main thread
    //also ther is invokeAny it will return whichever complete first
    
    for(Future<Integer> f:futures){
        System.out.println(f.get());
        //fi.isDone() either task complete or cancel/exception , Cancel(true(cancel in any case)/false(do not interrupt task while running)) to cancel task, isCancelled(), .get(task,time) wait till given time else throw exception
    }
    System.out.println(i);
        executorService.shutdown();
        System.out.println(executorService.isShutdown());


    }
    
}
