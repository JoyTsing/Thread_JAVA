package future;

import java.util.concurrent.*;

/**
 * @description：演示get的超时方法，需要注意超时后处理， 调用future.cancel（）演示cancel传入true和false的区别，代表是否中断正在执行的任务
 */
public class timeOut {
    private static final Ad DEFAULT_AD = new Ad("默认");
    private static final ExecutorService exec = Executors.newFixedThreadPool(10);

    static class Ad {
        String name;

        public Ad(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Ad{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class FetchAdTask implements Callable<Ad> {

        @Override
        public Ad call() throws Exception {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("sleep期间被中断了");
                return DEFAULT_AD;
            }
            return new Ad("最新的");
        }
    }

    public void printAd() {
        Future<Ad> f = exec.submit(new FetchAdTask());
        Ad ad;

        try {
            ad = f.get(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            ad=new Ad("被中断时");
        } catch (ExecutionException e) {
            ad=new Ad("异常时候的");
        } catch (TimeoutException e) {
            ad = new Ad("超时时候的");
            System.out.println("超时啦");
            //是否直接通过interrupt停止线程
            boolean cancel = f.cancel(true);
            System.out.println("cancel的结果: " + cancel);
        }
        exec.shutdown();
        System.out.println(ad);
    }

    public static void main(String[] args) {
        timeOut timeOut = new timeOut();
        timeOut.printAd();
    }
}
