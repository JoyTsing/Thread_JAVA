package lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptibly implements Runnable {

    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockInterruptibly lockInterruptibly=new LockInterruptibly();
        Thread thread0=new Thread(lockInterruptibly);
        Thread thread1=new Thread(lockInterruptibly);

        thread0.start();
        thread1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread0.interrupt();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 尝试获取锁");
        try {
            lock.lockInterruptibly();
            try {
                System.out.println(Thread.currentThread().getName() + " get the lock");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() +" sleeping is interrupted");
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " release");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() +" get lock is interrupted");
        }
    }
}
