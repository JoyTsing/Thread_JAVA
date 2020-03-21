package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @description：sleep时候不释放synchronized的moitor，sleep时间到了才正常释放
 */
public class SleepDontReleaseMonitor implements Runnable{

    public static void main(String[] args) {
        SleepDontReleaseMonitor sleepDontReleaseMonitor=new SleepDontReleaseMonitor();
        new Thread(sleepDontReleaseMonitor).start();
        new Thread(sleepDontReleaseMonitor).start();
    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn(){
        System.out.println("线程"+Thread.currentThread().getName()+"获取到了monitor");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程"+Thread.currentThread().getName()+"退出了同步代码块");
    }
}
