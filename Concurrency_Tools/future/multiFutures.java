package future;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @description：批量提交任务时，用List批量接受结果
 */
public class multiFutures {

    public static void main(String[] args) {
        ExecutorService service= Executors.newFixedThreadPool(2);
        ArrayList<Future> futures=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Future<Integer> future = service.submit(() -> {
                Thread.sleep(3000);
                return new Random().nextInt();
            });
            futures.add(future);
        }

        for (int i = 0; i < 20; i++) {
            Future<Integer> future=futures.get(i);
            try {
                Integer integer = future.get();
                System.out.println(integer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        service.shutdown();
    }
}
