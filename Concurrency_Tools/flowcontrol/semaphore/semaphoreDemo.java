package flowcontrol.semaphore;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class semaphoreDemo {

    static Semaphore semaphore = new Semaphore(3, true);

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 100; i++) {
            service.submit(new Task());
        }
        service.shutdown();
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            try {
                semaphore.acquire(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "拿到许可");
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + "释放许可");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release(3);
            }
        }
    }
}
