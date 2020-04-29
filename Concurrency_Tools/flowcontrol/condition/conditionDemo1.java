package flowcontrol.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class conditionDemo1 {
    private ReentrantLock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    void method1() throws InterruptedException {
        lock.lock();
        try{
            System.out.println("条件不足 开始await");
            condition.await();
            System.out.println("条件满足，执行后续任务");
        }finally {
            lock.unlock();
        }

    }

    void method2(){
        lock.lock();
        try {
            System.out.println("准备工作完成，唤醒其他线程");
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        conditionDemo1 conditionDemo1=new conditionDemo1();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    conditionDemo1.method2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        conditionDemo1.method1();
    }
}
