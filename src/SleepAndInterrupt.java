/**
 * 章节 2.2.3 线程中断
 */
public class SleepAndInterrupt {

    /**
     * 线程中断机制： 之前有个stop方法但是被弃用，如果一个线程对一个对象有赋值操作强行中断会导致对象数据异常
     * root case：强行中断对象赋值会被打断形成错误数据
     * 所以通过interrupted方法增加一个标志 手动通过判断中断标志进行数据保存及安全退出
     */
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
