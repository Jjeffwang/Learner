package multi.Exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jeff on 2017/12/4.
 */
public class ExchangerTest {

    private static final Exchanger<String> exg=new Exchanger<>();
    private static ExecutorService executorService= Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String A="流水号A";
                try {
                    exg.exchange(A);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String B="流水号B";
                try {
                    String A=exg.exchange(B);
                    System.out.println("是否一致？");
                    System.out.println(A.equals(B));
                    System.out.println(A);
                    System.out.println(B);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
