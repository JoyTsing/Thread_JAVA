package improvecache;

import improvecache.computable.Computable;
import improvecache.computable.ExpensiveFunction;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @description： 利用future避免重复计算
 * 但是仍然有几率会重复
 */
public class cache7<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public cache7(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws Exception {
        Future<V> f = cache.get(arg);
        if (f == null) {
            FutureTask<V> ft = new FutureTask<>(() -> {
                return c.compute(arg);
            });
            f = ft;
            //放缓存操作放在真正开始计算之前
            cache.put(arg, ft);
            System.out.println("从FutureTask调用计算结果");
            ft.run();
        }
        return f.get();
    }

    public static void main(String[] args) throws Exception {
        cache7<String, Integer> computer = new cache7<>(new ExpensiveFunction());
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
