package future;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @description：get方法抛出异常时机，get执行时才抛出
 */
public class getException {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> future = service.submit(() -> {
            //不管这里抛出什么异常 捕获到的总是运行时异常
            throw new IllegalArgumentException("异常异常");
        });

        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
                Thread.sleep(200);
            }
            System.out.println("isDone? "+future.isDone());
            future.get();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException异常");
        } catch (ExecutionException e) {
            System.out.println("ExecutionException异常");
        }

        service.shutdown();
    }

}
