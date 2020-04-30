package improvecache;

import improvecache.computable.Computable;
import improvecache.computable.ExpensiveFunction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @description：采用原子组合操作填补漏洞
 */
public class cache8<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public cache8(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws Exception {
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
        return f.get();
    }

    public static void main(String[] args) throws Exception {
        cache8<String, Integer> computer = new cache8<>(new ExpensiveFunction());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer integer = computer.compute("666");
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
    }
}
