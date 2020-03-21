package jmm;

/**
 * @description：TODO
 */
public class FieldVisibility {
    volatile int a = 1, b = 2;

    public static void main(String[] args) {

        FieldVisibility test = new FieldVisibility();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.change();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.print();
            }
        }).start();


    }

    private void print() {
        System.out.println("b=" + b + " a=" + a);
    }

    private void change() {
        a = 3;
        b = a;
    }

}
