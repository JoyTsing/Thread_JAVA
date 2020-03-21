package jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description：TODO
 */
public class NoVolatile2 implements Runnable{
    volatile boolean done=false;
    AtomicInteger readA=new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Runnable r=new NoVolatile2();
        Thread thread1=new Thread(r);
        Thread thread2=new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(((NoVolatile2) r).done);
        System.out.println(((NoVolatile2) r).readA.get());
    }

    @Override
    public void run() {
        for (int i = 0; i <10000 ; i++) {
            setDone();
            readA.incrementAndGet();
        }
    }

    private void setDone() {
        done=!done;
    }
}
