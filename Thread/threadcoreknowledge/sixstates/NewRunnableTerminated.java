package threadcoreknowledge.sixstates;

/**
 * @description：3 states
 */
public class NewRunnableTerminated implements Runnable {

    public static void main(String[] args) {
        Thread thread = new Thread(new NewRunnableTerminated());
        //new
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(thread.getState());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(thread.getState());
    }

    @Override
    public void run() {

        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}
