package lock.reentrantlock;


import java.util.concurrent.locks.ReentrantLock;

public class RecursionDemo {

    private static ReentrantLock lock=new ReentrantLock();

    private static void accessResource(){
        lock.lock();
        try {
            System.out.println("process");
            if(lock.getHoldCount()<5){
                System.out.println(lock.getHoldCount());
                accessResource();
                System.out.println(lock.getHoldCount());
            }
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        accessResource();
    }
}
