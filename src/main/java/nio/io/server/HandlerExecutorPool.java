package nio.io.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 自定义线程池
 * Created by ${WangChengYong} on 2018/3/15.
 */
public class HandlerExecutorPool {

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public void execute(Runnable task) {
        executorService.execute(task);
    }
}
