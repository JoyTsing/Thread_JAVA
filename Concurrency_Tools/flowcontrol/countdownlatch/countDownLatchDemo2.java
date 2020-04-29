package flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多等一
 */
public class countDownLatchDemo2 {

    public static void main(String[] args) throws InterruptedException {
       CountDownLatch begin= new CountDownLatch(1);
        ExecutorService service= Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            int no=i+1;
           Runnable r= new Runnable(){
                @Override
                public void run() {
                    System.out.println("No."+no+"准备");
                    try {
                        begin.await();
                        System.out.println("No."+no+"开始了");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
           service.submit(r);
        }

        Thread.sleep(5000);
        System.out.println("总的开始了");
        begin.countDown();
    }

}
