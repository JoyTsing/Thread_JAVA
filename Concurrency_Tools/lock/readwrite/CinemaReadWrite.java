package lock.readwrite;


import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CinemaReadWrite {
    private static ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock readLock=reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock=reentrantReadWriteLock.writeLock();

    private static void read(){
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" got read Lock");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("release read lock");
            readLock.unlock();
        }
    }

    private static void write(){
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" got write Lock");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("release write lock");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(()->read(),"Thread1").start();
        new Thread(()->read(),"Thread2").start();
        new Thread(()->read(),"Thread3").start();
        new Thread(()->write(),"Thread4").start();
        new Thread(()->write(),"Thread5").start();
    }
}
