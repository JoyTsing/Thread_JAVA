package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @description：交替打印0-100的奇偶数，用wait/notify实现
 */
public class WaitNotifyPrintOddEvenWait {
    //1.拿到锁就打印
    //2.打印完就休眠,并唤醒其他线程
    private static int count = 0;

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new TurningRunner(),"偶数").start();
        Thread.sleep(100);
        new Thread(new TurningRunner(),"奇数").start();
    }

    static class TurningRunner implements Runnable {

        @Override
        public void run() {
            while (count <= 100) {
                synchronized (lock){
                    System.out.println(Thread.currentThread().getName()+":"+count++);
                    lock.notify();
                    if(count<=100){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }

}
