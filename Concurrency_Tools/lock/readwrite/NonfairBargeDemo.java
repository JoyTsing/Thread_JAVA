package lock.readwrite;


import java.util.concurrent.locks.ReentrantReadWriteLock;

public class NonfairBargeDemo {

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);

    private static ReentrantReadWriteLock.ReadLock readLock= reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock=reentrantReadWriteLock.writeLock();

    private static void setReentrantReadWriteLock(){
        
    }
}
