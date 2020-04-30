package improvecache;

import improvecache.computable.Computable;
import improvecache.computable.ExpensiveFunction;
import improvecache.computable.MayFail;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @description：可能计算失败的结果
 */
public class cache9<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public cache9(Computable<A, V> c) {
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
            }catch (CancellationException e){
                System.out.println("被取消了");
                cache.remove(arg);
                throw  e;
            } catch (InterruptedException e) {
                cache.remove(arg);
                throw  e;
            } catch (ExecutionException e) {
                System.out.println("计算错误，需要重试");
                cache.remove(arg);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        cache9<String, Integer> computer = new cache9<>(new MayFail());
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


//        Thread.sleep(1000);
//        computer.cache.get("666").cancel(true);
        
    }

}
