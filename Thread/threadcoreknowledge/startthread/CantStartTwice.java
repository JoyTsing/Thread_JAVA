package threadcoreknowledge.startthread;

public class CantStartTwice {
    public static void main(String[] args) {
        Thread thread=new Thread();
        thread.start();
        thread.start();
    }

}
