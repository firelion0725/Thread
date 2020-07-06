import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtils {
    private static ExecutorService service = Executors.newFixedThreadPool(5, r -> {
        Thread thread = new Thread(r);
        thread.setName("ThreadPoolUtils");
        return thread;
    });

    public static ExecutorService getService() {
        return service;
    }
}
