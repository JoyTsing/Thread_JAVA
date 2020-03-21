package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description：TODO
 */
public class SleepDontReleaseLock implements Runnable{
    private static final Lock Lock=new ReentrantLock();


    @Override
    public void run() {
        Lock.lock();
        System.out.println("线程"+Thread.currentThread().getName()+"获取到了锁");
        try {
            Thread.sleep(2000);
            System.out.println("线程"+Thread.currentThread().getName()+"唤醒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            Lock.unlock();
        }
    }

    public static void main(String[] args) {
        SleepDontReleaseLock sleepDontReleaseLock=new SleepDontReleaseLock();
        new Thread(sleepDontReleaseLock).start();
        new Thread(sleepDontReleaseLock).start();
    }
}
