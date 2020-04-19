package atomic;


import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicArray {
    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(1000);
        Incrementer incrementer = new Incrementer(atomicIntegerArray);
        Decrementer decrementer = new Decrementer(atomicIntegerArray);
        Thread[] threadsIncrementer = new Thread[100];
        Thread[] threadsDecrementer = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threadsDecrementer[i] = new Thread(decrementer);
            threadsIncrementer[i] = new Thread(incrementer);
            threadsDecrementer[i].start();
            threadsIncrementer[i].start();
        }
//        Thread.sleep(10000);
        for (int i = 0; i < 100; i++) {
            threadsDecrementer[i].join();
            threadsIncrementer[i].join();
        }

        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            if(atomicIntegerArray.get(i)!=0){
                System.out.println("非零值" +i);
            }
        }
        System.out.println("end");
    }
}

class Decrementer implements Runnable {
    private AtomicIntegerArray array;

    public Decrementer(AtomicIntegerArray array) {
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndDecrement(i);
        }
    }
}

class Incrementer implements Runnable {
    private AtomicIntegerArray array;

    public Incrementer(AtomicIntegerArray array) {
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndIncrement(i);
        }
    }
}