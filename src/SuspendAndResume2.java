/**
 * 章节 2.2.5
 * suspend 和 resume 为废弃方法 文中提供了 wait 和notify的实现例子
 */
public class SuspendAndResume2 {

    private static Object lock = new Object();

    public static void main(String[] args) {
        ChangeObjectThread t1 = new ChangeObjectThread();
        ReadObjectThread t2 = new ReadObjectThread();

        t1.start();
        t2.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.suspendMe();
        System.out.println("t1 suspend");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("resume t1");
        t1.resumeMe();
    }

    private static class ChangeObjectThread extends Thread {
        private volatile boolean suspendMe = false;

        public void suspendMe() {
            suspendMe = true;
        }

        public void resumeMe() {
            suspendMe = false;
            synchronized (this) {
                notify();
            }
        }

        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    while (suspendMe) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                synchronized (lock) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("in ChangeObjectThread ");
                }
                Thread.yield();
            }
        }
    }

    private static class ReadObjectThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("in ReadObjectThread ");
                }
                Thread.yield();
            }
        }
    }
}
