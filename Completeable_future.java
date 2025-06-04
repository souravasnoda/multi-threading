
//multi-threading is used to achive Aync program

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Completeable_future {
    public static void main(String[] args) {
       CompletableFuture<String> completableFuture= CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                // TODO: handle exception
            }
            return "OK";
        });
        String s =null;
        try {
            s =completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String str =completableFuture.getNow("nooo");
        System.out.println(str);
        System.out.println(s);
        System.out.println("MAIN");
    }
}
