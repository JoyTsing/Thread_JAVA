package threadcoreknowledge.threadobjectclasscommonmethods;

public class WaitNotifyAll implements Runnable{

    private  static  final Object resourceA = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable r=new WaitNotifyAll();
        Thread threadA=new Thread(r);
        Thread threadB=new Thread(r);
        threadA.start();
        threadB.start();
        Thread.sleep(200);
        new Thread(()->{
            synchronized (resourceA){
                resourceA.notifyAll();
                System.out.println("ThreadC notified.");
            }
        }).start();
    }

    @Override
    public void run() {
        synchronized (resourceA){
            System.out.println(Thread.currentThread().getName()+" got resourceA lock.");

            try {
                System.out.println(Thread.currentThread().getName()+" waits to start");
                resourceA.wait();
                System.out.println(Thread.currentThread().getName()+" is waiting to end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
