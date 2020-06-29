/**
 * 2.3 volatile与java内存模型（JMM）
 */
public class Volatile {
    //内存模型 三大特性 原子性 有序性 可见性

    //这个例子证明volatile没有原子性，volatile不能阻止多线程读写的隔离，当发生同时读写的情况无法排他，保证数据准确
    private volatile static int i = 0;

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        for (int j = 0; j < threads.length; j++) {
            threads[j] = new Thread(new PlusTask());
            threads[j].start();
        }

        for (int j = 0; j < threads.length; j++) {
            try {
                threads[j].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(i);
    }

    private static class PlusTask implements Runnable {

        @Override
        public void run() {
            for (int j = 0; j < 1000; j++) {
                i++;
            }
        }
    }

}
