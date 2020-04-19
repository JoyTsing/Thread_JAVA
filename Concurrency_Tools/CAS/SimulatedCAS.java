package CAS;


public class SimulatedCAS {
    private volatile int value;
    public synchronized int compareAndSwap(int expectedValue,int newValue){
        int oldValue=value;
        if(oldValue==expectedValue){
            value=newValue;
        }
        return oldValue;
    }
}
