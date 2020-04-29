package future;

import java.util.concurrent.*;


/**
 * @description：TODO
 */
public class FutureTaskDemo {

    public static void main(String[] args) {
        Task task = new Task();
        FutureTask<Integer> integerFutureTask = new FutureTask<>(task);
        // new Thread(integerFutureTask).start();
        ExecutorService service= Executors.newCachedThreadPool();
        service.submit(integerFutureTask);

        try {
            System.out.println("Task运行结果" + integerFutureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }

}

class Task implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("calculate");
        Thread.sleep(1500);
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }
}
