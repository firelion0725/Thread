/**
 * 章节 2.2.3 线程中断
 */
public class SleepAndInterrupt {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("当前线程执行中");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    //sleep 被中断会抛出中断异常 并且清除中断标志
//                    System.out.println("当前睡眠进入中断执行 保存状态并退出循环");
//                    break;
                    //如果无需保存数据现场可以直接退出如果需要 则可以再次进行中断标志
                    Thread.currentThread().interrupt();
                }
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("当前线程进入中断执行 保存状态并退出循环");
                    break;
                }
            }

            System.out.println("当前进程退出");
        });
        thread.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }
}
