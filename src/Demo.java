
public class Demo {

    public static void main(String[] args) {
        ThreadPoolUtils.getService().execute(() -> {
            Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
            String oldName = Thread.currentThread().getName();
            Thread.currentThread().setName("Test Runnable");

            Thread.currentThread().setName(oldName);
        });
    }
}
