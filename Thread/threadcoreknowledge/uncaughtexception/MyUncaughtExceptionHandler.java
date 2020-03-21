package threadcoreknowledge.uncaughtexception;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private String name;

    public MyUncaughtExceptionHandler(String name) {
        this.name = name;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger=Logger.getAnonymousLogger();
        logger.log(Level.WARNING,"线程异常，终止"+t.getName(),e);
        System.out.println(name+"捕获了异常"+t.getName()+"异常"+e);
    }

}
