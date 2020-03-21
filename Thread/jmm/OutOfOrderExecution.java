package jmm;

import java.util.concurrent.CountDownLatch;

/**
 * @description：TODO
 */
public class OutOfOrderExecution {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        //辅助工具类类似于闸门
        while (true) {
            i++;
            x = y = a = b = 0;
            CountDownLatch latch = new CountDownLatch(1);
            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    a = 1;
                    x = b;
                }
            });

            Thread two = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    b = 1;
                    y = a;
                }
            });

            one.start();
            two.start();
            latch.countDown();
            one.join();
            two.join();
            String result="第"+i+"次: "+"x=" + x + " ,y=" + y;
            if (x == 0 && y == 0) {
                System.out.println(result);
                break;
            }
        }
    }


}
