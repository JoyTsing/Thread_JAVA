package jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description：TODO
 */
public class UseVolatile1 implements Runnable {
    volatile boolean done=false;
    AtomicInteger readA=new AtomicInteger();
    public static void main(String[] args) throws InterruptedException {
        Runnable r=new UseVolatile1();
        Thread thread1=new Thread(r);
        Thread thread2=new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(((UseVolatile1) r).done);
        System.out.println(((UseVolatile1) r).readA.get());
    }

    @Override
    public void run() {
        for (int i = 0; i <10000 ; i++) {
            setDone();
            readA.incrementAndGet();
        }
    }

    private void setDone() {
        done=true;
    }
}
