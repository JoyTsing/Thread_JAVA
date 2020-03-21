package threadcoreknowledge.createthreads.wrongways;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
public class ThreadPool5 {

    public static void main(String[] args) {
        ExecutorService executorService=Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.submit(new meTask() {
            });
        }
        
    }
}

class meTask implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}