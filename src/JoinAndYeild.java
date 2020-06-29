import static java.lang.Thread.sleep;

/**
 * 2.2.6 等待线程结束和谦让
 */
public class JoinAndYeild {
    private static volatile int i = 0;

    public static void main(String[] args) {
        AndThread at = new AndThread();
        at.start();
        //不等待at线程直接拿返回值通常为0或者1 这样的较小的值
        System.out.println(i);
        try {
            at.join();
            //可以尝试等待时间
//            at.join(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i);
    }

    private static class AndThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
                if(i == 1000){
                    break;
                }
            }

        }
    }
}
