package atomic;


import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo1 implements Runnable{

    private static final AtomicInteger atomicInteger=new AtomicInteger();

    public void incrementAtomic(){
        atomicInteger.getAndIncrement();
    }

    private static volatile int basicCount=0;

    public void incrementBasic(){
        basicCount++;
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo1 r = new AtomicIntegerDemo1();
        Thread t1=new Thread(r);
        Thread t2=new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("原子类的结果"+atomicInteger.get());
        System.out.println(basicCount);
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            incrementAtomic();
            incrementBasic();
        }
    }
}
