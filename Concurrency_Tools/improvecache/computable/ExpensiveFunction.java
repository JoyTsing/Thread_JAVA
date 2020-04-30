package improvecache.computable;

/**
 * @description：耗时计算，实现Computable接口，不具有缓存功能
 */
public class ExpensiveFunction implements Computable<String, Integer> {

    @Override
    public Integer compute(String arg) throws Exception {
        Thread.sleep(3000);
        return Integer.valueOf(arg);
    }
}
