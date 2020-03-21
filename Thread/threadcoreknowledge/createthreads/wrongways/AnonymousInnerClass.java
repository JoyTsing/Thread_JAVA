package threadcoreknowledge.createthreads.wrongways;

import threadcoreknowledge.createthreads.ThreadStyle;

public class AnonymousInnerClass {

    public static void main(String[] args) {

        new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();

    }
}
