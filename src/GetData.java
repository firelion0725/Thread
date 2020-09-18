import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class GetData {

    private final Object lock = new Object();
    private final AtomicInteger index = new AtomicInteger(0);
    private final AtomicBoolean isRun1 = new AtomicBoolean(true);

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        GetData gd = new GetData();
        gd.getData(nums);
    }

    public void getData(int[] nums) {
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                while (index.get() < nums.length) {
                    if (isRun1.get()) {
                        isRun1.set(false);
                        System.out.println("thread1 - " + nums[index.get()]);
                        index.incrementAndGet();
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                while (index.get() < nums.length) {
                    if (!isRun1.get()) {
                        isRun1.set(true);
                        System.out.println("thread2 - " + nums[index.get()]);
                        index.incrementAndGet();
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }

}
