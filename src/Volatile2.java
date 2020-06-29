import com.sun.corba.se.pept.transport.ReaderThread;
import sun.security.krb5.RealmException;

/**
 * 2.3 volatile与java内存模型（JMM）
 * 证明volatile的可见性 当线程刚启动时 变更ready 有volatile关键字 则被reader线程观察到
 */
public class Volatile2 {

        private static boolean ready = false;
//    private static volatile boolean ready = false;
        private static int number = 0;
//    private static volatile int number = 0;

    public static void main(String[] args) {
        ReaderThread rt = new ReaderThread();
        rt.start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        number = 40;
        ready = true;
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ready = false;
    }

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (ready) {
//                try {
//                    sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println(number);
            }
            System.out.println("thread finish");
        }
    }
}
