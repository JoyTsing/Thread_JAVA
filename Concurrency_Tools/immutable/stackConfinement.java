package immutable;

/**
 * @description：栈封闭的两种情况 基本变量和对象先演示线程争抢带来的错误结果 然后把变量放到方法内 情况才变
 */
public class stackConfinement implements Runnable {
    int index = 0;

    public void inThread() {
        int num = 0;
        for (int i = 0; i < 10000; i++) {
            num++;
        }
        System.out.println("栈内保护的数字是线程安全的" + num);
    }


    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            index++;
        }
        inThread();
    }

    public static void main(String[] args) throws InterruptedException {
        stackConfinement r1 = new stackConfinement();
        Thread thread1 = new Thread(r1);
        Thread thread2 = new Thread(r1);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(r1.index);
    }
}
