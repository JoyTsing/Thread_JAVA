package lock.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description：TODO
 */
public class TryLockDeadLock implements Runnable {

    int flag = 1;
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        TryLockDeadLock r1 = new TryLockDeadLock();
        TryLockDeadLock r2 = new TryLockDeadLock();
        r1.flag=1;
        r2.flag=2;
        new Thread(r1).start();
        new Thread(r2).start();
    }


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {


            if (flag == 1) {
                try {
                    if (lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println("1 got lock1");
                            Thread.sleep(new Random().nextInt(1000));

                            if(lock2.tryLock(800,TimeUnit.MILLISECONDS)){
                                try {
                                    System.out.println("1 got lock 2");
                                    System.out.println("1 got 2 locks");
                                    break;
                                }finally {
                                    lock2.unlock();
                                    Thread.sleep(new Random().nextInt(1000));
                                }
                            }else{
                                System.out.println("1 got lock 2 faild");
                            }

                        } finally {
                            lock1.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    } else {
                        System.out.println("1 got lock1 failed,try got one");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (flag == 2) {
                try {
                    if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println("2 got lock2");
                            Thread.sleep(new Random().nextInt(1000));

                            if(lock1.tryLock(800,TimeUnit.MILLISECONDS)){
                                try {
                                    System.out.println("2 got lock 1");
                                    System.out.println("2 got 2 locks");
                                    break;
                                }finally {
                                    lock1.unlock();
                                    Thread.sleep(new Random().nextInt(1000));
                                }
                            }else{
                                System.out.println("2 got lock 1 failed");
                            }

                        } finally {
                            lock2.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    } else {
                        System.out.println("2 got lock2 failed,try got one");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
