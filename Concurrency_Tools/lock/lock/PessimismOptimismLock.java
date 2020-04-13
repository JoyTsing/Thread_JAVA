package lock.lock;


import java.util.concurrent.atomic.AtomicInteger;

public class PessimismOptimismLock {
    int a;
    public synchronized void testMethod(){
        a++;
    }

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();

    }
}
