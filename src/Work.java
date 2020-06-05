public class Work {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("当前线程执行中");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    System.out.println("当前睡眠进入中断执行 保存状态并退出循环");
                    break;
                }
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("当前进入中断执行 保存状态并退出循环");
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
