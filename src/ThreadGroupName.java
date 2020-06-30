/**
 * 2.4 线程组
 */
public class ThreadGroupName implements Runnable {

    public static void main(String[] args) {
        ThreadGroup tg = new ThreadGroup("tg");
        Thread t1 = new Thread(tg, new ThreadGroupName(), "T1");
        Thread t2 = new Thread(tg, new ThreadGroupName(), "T2");

        t1.start();
        t2.start();
        System.out.println(tg.activeCount());
        tg.list();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //tg 统一处理
        tg.interrupt();
    }

    @Override
    public void run() {
        String groupAndName = Thread.currentThread().getThreadGroup().getName()
                + "-" + Thread.currentThread().getName();

        while (true) {
            System.out.println("I am " + groupAndName);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(groupAndName + " is interrupted");
                break;
            }
        }
    }
}
