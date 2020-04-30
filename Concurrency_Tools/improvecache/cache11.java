package improvecache;

import improvecache.computable.Computable;
import improvecache.computable.MayFail;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @description：出于安全考虑 缓存需要设置有效期，到期自动失效
 * 解决缓存的问题 设置随机的缓存时间
 */
public class cache11<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;
    public final static ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

    public cache11(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException, ExecutionException {
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                FutureTask<V> ft = new FutureTask<>(() -> {
                    return c.compute(arg);
                });
                //改进地点,相当于两个锁
                //放缓存操作放在真正开始计算之前
                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                    f = ft;
                    System.out.println("从FutureTask调用计算结果");
                    ft.run();
                }
            }
            try {
                /*
                 *三种异常的逻辑不一样
                 * CancellationException和InterruptedException是人为取消的
                 * 那么我们应该立即终止任务
                 * 但如果是计算错误 在明确知道多尝试几次就可以得到答案的话应该重试，直到正确结果出现
                 */
                return f.get();
            } catch (CancellationException e) {
                System.out.println("被取消了");
                cache.remove(arg);
                throw e;
            } catch (InterruptedException e) {
                cache.remove(arg);
                throw e;
            } catch (ExecutionException e) {
                System.out.println("计算错误，需要重试");
                cache.remove(arg);
            }
        }
    }

    public void shutDown() {
        executor.shutdown();
    }


    public V compute(A arg, long expire) throws ExecutionException, InterruptedException {
        if (expire > 0) {
            executor.schedule(() -> {
                expire(arg);
            }, expire, TimeUnit.MILLISECONDS);
        }
        return compute(arg);
    }

    private synchronized void expire(A key) {
        Future<V> future = cache.get(key);
        if (future != null) {
            if (future.isDone()) {
                System.out.println("Future任务取消");
                future.cancel(true);
            }

            System.out.println("过期时间到,缓存清除");
            cache.remove(key);
        }

    }

    public static void main(String[] args) throws Exception {
        cache11<String, Integer> computer = new cache11<>(new MayFail());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer integer = computer.compute("666", 5000L);
                    System.out.println("第一个线程计算结果:" + integer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer integer = computer.compute("666");
                    System.out.println("第三个线程计算结果:" + integer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer integer = computer.compute("667");
                    System.out.println("第二个线程计算结果:" + integer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


//        Thread.sleep(1000);
//        computer.cache.get("666").cancel(true);

        Thread.sleep(6000L);
        Integer result = computer.compute("666");
        System.out.println("第四次计算结果:" + result);
        computer.shutDown();
    }

}
