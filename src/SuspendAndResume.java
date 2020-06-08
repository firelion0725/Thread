/**
 * 章节 2.2.5 挂起和继续执行
 *
 * 首先这两个方法已经被废弃了
 */
public class SuspendAndResume {

    public static Object lock = new Object();

    public static class ChangeObjectThread extends Thread {
        @Override
        public void run() {
            synchronized (lock){
                System.out.println("in "+ getName());
                Thread.currentThread().suspend();
            }
        }
    }
}
