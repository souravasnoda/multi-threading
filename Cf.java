import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
public class Cf{
    public static void main(String[] args) {
        //Used for non-blocking/async execution.
        //Its a deamon thread so no thread will wait for it
        //if we want to watit we have to use .get() to get its result
        CompletableFuture<String> cf1 =CompletableFuture.supplyAsync(()->{
            try {
            
                Thread.sleep(2000);
                System.out.println("THread");
            } catch (Exception e) {
                // TODO: handle exception
            }
            return "OK";
        });

        CompletableFuture<String> cf2 =CompletableFuture.supplyAsync(()->{
            try {
            
                Thread.sleep(1000);
                System.out.println("Thread2");
            } catch (Exception e) {
                // TODO: handle exception
            }
            return "OK";
        });// we can use method directly here like get(but it will be blocking because main thread will wait untill this get executed),thenApply

      CompletableFuture<Void> f=  CompletableFuture.allOf(cf1,cf2);
      f.join();
      
String s= null;
try {
     s = cf1.get();
} catch (InterruptedException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
} catch (ExecutionException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
}
System.out.println(s);
System.out.println("MAIN");
  
}}
