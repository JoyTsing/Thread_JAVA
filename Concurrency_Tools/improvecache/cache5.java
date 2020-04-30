package improvecache;

import improvecache.computable.Computable;
import improvecache.computable.ExpensiveFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description：
 * 在计算完成前，另一个要求计算相同值请求到来会导致计算两遍，与缓存避免多次计算的初衷相反
 */
public class cache5<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public cache5(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws Exception {
        System.out.println("进入缓存机制");
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        cache5<String, Integer> computer = new cache5<>(new ExpensiveFunction());
        Integer result = computer.compute("666");
        System.out.println("第一次计算结果" + result);
        result = computer.compute("666");
        System.out.println("第二次计算结果" + result);
    }
}
