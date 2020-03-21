package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @description：交替打印0-100的奇偶数，用synchronized实现
 */
public class WaitNotifyPrintOddEvenSyn {

    private static int count;
    private static Object lock=new Object();

    //新建两个线程
    //1个只处理偶数，1个只处理奇数(使用位运算)
    //用synchronized来通信
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100) {
                    synchronized (lock) {
                        if ((count & 1) == 0) {
                            System.out.println(Thread.currentThread().getName() + ":" + count++);
                        }
                    }

                }
            }
        }, "偶数").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100) {
                    synchronized (lock) {
                        if ((count & 1) == 1) {
                            System.out.println(Thread.currentThread().getName() + ":" + count++);
                        }
                    }

                }
            }
        }, "奇数").start();


    }


}
