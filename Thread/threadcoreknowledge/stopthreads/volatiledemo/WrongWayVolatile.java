package threadcoreknowledge.stopthreads.volatiledemo;

/**
 * @description：看似可行其实不行
 */
public class WrongWayVolatile implements Runnable {
    private volatile boolean canceled = false;


    @Override
    public void run() {
        int num = 0;
        try {
            while (num < 10000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num + "是一百的倍数");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile r=new WrongWayVolatile();
        new Thread(r).start();
        Thread.sleep(5000);
        r.canceled=true;
    }

}
