package threadcoreknowledge.stopthreads;

/**
 * @description：run无法抛出checked Exception,只能try/catch
 */
public class RunThrowException {
    public void aVoid() throws Exception{
        throw new Exception();
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}