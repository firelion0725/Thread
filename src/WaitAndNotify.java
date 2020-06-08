/**
 * 章节 2.2.4  等待和通知
 */
public class WaitAndNotify {
    final static Object lock = new Object();

    public static void main(String[] args) {
        T1 t1 = new T1();
        T2 t2 = new T2();

        t1.start();
        t2.start();
    }

    static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("T1线程执行开始");
                try {
                    System.out.println("T1线程执行中");
                    //这里可以设定挂起时间 如果挂起小于 通知 直接继续执行，大于通知则被唤醒
                    //现象为 若T1先结束则为自己结束，T2先结束 则T1被T2唤醒
                    lock.wait();
//                    lock.wait(1000);
//                    lock.wait(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("T1线程执行结束");
        }
    }

    static class T2 extends Thread {
        @Override
        public void run() {
            System.out.println("T2线程执行开始");

            try {
                Thread.sleep(5000);
                System.out.println("T2线程执行中");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock){

                lock.notify();
                System.out.println("T2线程执行结束");
            }

        }
    }
}
