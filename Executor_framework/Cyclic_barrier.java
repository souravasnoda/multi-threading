package Executor_framework;

import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Cyclic_barrier {
    public static void main(String[] args) throws ExecutionException,InterruptedException{
        int numberOfServices = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CyclicBarrier barrier = new CyclicBarrier(numberOfServices);
        executorService.submit(new CyclicBarrierService(barrier));
        executorService.submit(new CyclicBarrierService(barrier));
        executorService.submit(new CyclicBarrierService(barrier));
        System.out.println("Main");
        barrier.getNumberWaiting();
        barrier.getParties();
        barrier.reset();
        executorService.shutdown();
    }

}

class CyclicBarrierService implements Callable<String> {
    private final CyclicBarrier barrier;

    public CyclicBarrierService(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " service started");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " is Waiting at barrier.");
        barrier.await();
        return "OK";
    }

}
