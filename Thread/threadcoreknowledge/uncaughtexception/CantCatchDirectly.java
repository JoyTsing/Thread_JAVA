package threadcoreknowledge.uncaughtexception;

/**
 * @description： 1.不加try catch抛出4个异常都带线程名字
 * 2. 加了try catch，期望捕获到第一个线程的异常，线程234不应该运行希望看到打印出的caught Exception
 * 3. 执行时发现，根本没有Caught Exception，线程234也没有抛出任何异常
 * <p>
 * 线程异常不能用传统方法捕获
 */
public class CantCatchDirectly implements Runnable {

    public static void main(String[] args) throws InterruptedException {

            new Thread(new CantCatchDirectly(), "MyThread-1").start();
            Thread.sleep(300);
            new Thread(new CantCatchDirectly(), "MyThread-2").start();
            Thread.sleep(300);
            new Thread(new CantCatchDirectly(), "MyThread-3").start();
            Thread.sleep(300);
            new Thread(new CantCatchDirectly(), "MyThread-4").start();


    }

    @Override
    public void run() {
        try {
            throw new RuntimeException();
        }catch (RuntimeException e){
            System.out.println("Caught");
        }
    }
}
