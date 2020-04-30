package improvecache;


import improvecache.computable.ExpensiveFunction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class test12 {
    static cache10<String, Integer> expensiveComputer = new cache10<>(
            new ExpensiveFunction()
    );

    //打底就有沉睡的5s 用countdownLatch压力测试
    public static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(100);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            service.submit(() -> {
                Integer result = null;
                try {
                    System.out.println(Thread.currentThread().getName() + "开始等待");
                    countDownLatch.await();
                    SimpleDateFormat dateFormat = ThreadSafeFormatter.dateFormatter.get();
                    String time = dateFormat.format(new Date());
                    System.out.println(Thread.currentThread().getName() + " " + time + "被放行");
                    result = expensiveComputer.compute("666");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                System.out.println(result);
            });
        }
        /**
         * 第一次测试
         */
//        service.shutdown();
//        while (!service.isTerminated()) {
//
//        }
//        System.out.println("总耗时" + (System.currentTimeMillis() - start));
        /**
         * 第二次测试
         * 用ThreadLocal
         */
        Thread.sleep(5000);
        countDownLatch.countDown();
        service.shutdown();

    }
}

class ThreadSafeFormatter {

    public static ThreadLocal<SimpleDateFormat> dateFormatter = new ThreadLocal<SimpleDateFormat>() {

        //每个线程会调用本方法一次，用于初始化
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("mm:ss");
        }

        //首次调用本方法时，会调用initialValue()；后面的调用会返回第一次创建的值
        @Override
        public SimpleDateFormat get() {
            return super.get();
        }
    };
}
