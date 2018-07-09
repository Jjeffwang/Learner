package guava;

import com.google.common.util.concurrent.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * Created with IDEA
 * author:wang
 * Date:2018/7/6 0006 下午 5:32
 * Description: guava  future
 */
public class future {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 一：线程完成后执行
         */
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<String> future = service.submit(() -> "result");
        System.out.println(future.get());


        /**
         * 二： 注册回调方法，在线程执行完成后执行
         */
        Futures.addCallback(future, new FutureCallback() {
            @Override
            public void onSuccess(Object o) {
                System.out.println("success");
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("fail");
            }
        });
    }
}
