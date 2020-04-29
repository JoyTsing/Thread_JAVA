package aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class oneShotLatch {
    /*
    AQS实现的一个简单的线程协作器
     */
    private final Sync sync = new Sync();

    public void signal() {
        sync.releaseShared(0);
    }

    public void await() {
        sync.tryAcquireShared(0);
    }

    private class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }

        @Override
        protected int tryAcquireShared(int arg) {
            return (getState() == 1) ? 1 : -1;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        oneShotLatch latch = new oneShotLatch();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"尝试获取latch,失败就等待");
                    latch.await();
                    System.out.println("开闸"+Thread.currentThread().getName()+"继续运行");
                }
            }).start();
        }
        Thread.sleep(5000);
        latch.signal();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"尝试获取latch,失败就等待");
                latch.await();
                System.out.println("开闸"+Thread.currentThread().getName()+"继续运行");
            }
        }).start();
    }
}
