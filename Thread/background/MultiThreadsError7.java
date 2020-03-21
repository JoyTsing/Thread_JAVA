package background;

/**
 * @description：TODO
 */
public class MultiThreadsError7 {

    int count;

    private EventListener listener;

    private MultiThreadsError7(MultiThreadsError7.MySource source) {
        listener = new MultiThreadsError7.EventListener() {
            @Override
            public void onEvent(MultiThreadsError7.Event e) {
                System.out.println("\n我得到的数字是" + count);
            }
        };
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static MultiThreadsError7 getInstance(MySource source) {
        MultiThreadsError7 safeListener = new MultiThreadsError7(source);
        source.registerListener(safeListener.listener);
        return safeListener;

    }


    public static void main(String[] args) {
        MultiThreadsError7.MySource mySource = new MultiThreadsError7.MySource();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mySource.eventCome(new MultiThreadsError7.Event() {
                });
            }
        }).start();
        MultiThreadsError7 multiThreadsError7 = new MultiThreadsError7(mySource);
    }

    static class MySource {

        private MultiThreadsError7.EventListener listener;

        void registerListener(MultiThreadsError7.EventListener eventListener) {
            this.listener = eventListener;
        }

        void eventCome(MultiThreadsError7.Event e) {
            if (listener != null) {
                listener.onEvent(e);
            } else {
                System.out.println("还未初始化完毕");
            }
        }

    }

    interface EventListener {

        void onEvent(Event e);
    }

    interface Event {

    }


}
