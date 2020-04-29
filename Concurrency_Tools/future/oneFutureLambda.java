package future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @description：单个Future使用方法
 */
public class oneFutureLambda {

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(10);

        Callable<Integer> callable=()->{
            Thread.sleep(3000);
            return new Random().nextInt();
        };

        Future<Integer> future = service.submit(callable);
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }

}
