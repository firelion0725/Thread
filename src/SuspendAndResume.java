/**
 * 章节 2.2.5 挂起和继续执行
 * 挂起和执行是一对相反操作，挂起suspend的线程必须resume才能继续执行
 * <p>
 * suspend导致线程暂停时不会释放任何资源 如果resume操作意外在suspend之前发生 那线程可能很难会继续执行
 * 其他线程由于访问该线程占用的锁时都会被牵连 导致整个系统工作不正常。
 * <p>
 * 这两个方法已经被废弃了
 */
public class SuspendAndResume {

    public static Object lock = new Object();

    private static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    private static ChangeObjectThread t2 = new ChangeObjectThread("t2");


    /**
     * 实验现象：
     * t1 t2 先后开启并对lock临界区访问
     * 之后再先后 resume 目的是让线程继续执行
     *
     * 结果程序并未执行完毕 而是被挂起 t2
     *
     * root case： 虽然t2调用resume但是由于时间先后而未生效 导致t2被永远挂起
     * 通过Terminal 命令行： jstack -l pid 查看
     * t2 state为RUNNABLE
     *
     */
    public static void main(String[] args) {
        t1.start();
        //去掉了观察结果
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        t2.start();
        t1.resume();
        //增加一秒睡眠观察结果
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        t2.resume();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class ChangeObjectThread extends Thread {

        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("in: " + getName());
                Thread.currentThread().suspend();
            }
        }
    }
}
