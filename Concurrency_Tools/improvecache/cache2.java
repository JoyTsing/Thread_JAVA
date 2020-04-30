package improvecache;

import improvecache.computable.Computable;
import improvecache.computable.ExpensiveFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * @description：装饰器模式给计算器自动添加缓存功能 先实现计算和缓存解耦
 * 问题 性能差未解决 当多个线程同时想计算需要等待，可能比不使用缓存还要差
 */
public class cache2<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new HashMap();
    private final Computable<A, V> c;

    public cache2(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A arg) throws Exception {
        System.out.println("进入缓存机制");
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        cache2<String, Integer> computer = new cache2<>(new ExpensiveFunction());
        Integer result = computer.compute("666");
        System.out.println("第一次计算结果" + result);
        result = computer.compute("666");
        System.out.println("第二次计算结果" + result);
    }
}
