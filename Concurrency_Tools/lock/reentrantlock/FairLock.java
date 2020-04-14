package lock.reentrantlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description：公平锁和非公平锁
 */
public class FairLock {

    public static void main(String[] args) {
        printQueue printQueue=new printQueue();
        Thread thread[]=new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i]=new Thread(new jobs(printQueue));
        }
        for (int i = 0; i < 10; i++) {
            thread[i].start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class jobs implements Runnable{

    printQueue printQueue;

    public jobs(lock.reentrantlock.printQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"正在打印");
        printQueue.printJob(new Object());
    }
}

class printQueue {
    private Lock queueLock = new ReentrantLock(false);

    public void printJob(Object doucument) {
        queueLock.lock();
        try {
            int duration = new Random().nextInt(5)+1;
            System.out.println(Thread.currentThread().getName()+" wait a second" + duration);
            Thread.sleep(duration*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }

        queueLock.lock();
        try {
            int duration = new Random().nextInt(5)+1;
            System.out.println(Thread.currentThread().getName()+" wait a second" + duration);
            Thread.sleep(duration*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }
    }
}

