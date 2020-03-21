package background;

/**
 * @description：TODO
 */
public class MultiThreadsError4 {
    static point point;

    public static void main(String[] args) throws InterruptedException {
        new pointMaker().start();
        Thread.sleep(150);
        if(point!=null){
            System.out.println(point);
        }
    }

}

class point{
    private final int x,y;

    public point(int x, int y) throws InterruptedException {
        this.x = x;
        MultiThreadsError4.point=this;
        Thread.sleep(100);
        this.y = y;
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y;
    }
}

class pointMaker extends Thread{

    @Override
    public void run() {
        try {
            new point(1,1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}