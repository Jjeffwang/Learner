package java8.completableFuture;

import java8.partitionBy.DomeVo;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;


public class MainTest {

    @Test
    public void test1() {
        Future<Double> future = CompletableFutureDemo.getPriceAsync("this is demo");
        // .....do something else
        try {
            double price = future.get();
            System.out.println(price);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test2() {
        long start = System.currentTimeMillis();
        List<DomeVo> domeVos = Arrays.asList(new DomeVo(1, true),
                new DomeVo(2, false),
                new DomeVo(3, true),
                new DomeVo(4, true),
                new DomeVo(5, false),
                new DomeVo(6, true),
                new DomeVo(7, true),
                new DomeVo(8, false));
        List<CompletableFuture<Integer>> list = domeVos.stream().map(domeVo -> CompletableFuture.supplyAsync(domeVo::getCode))
                .collect(Collectors.toList());
        List<Integer> result = list.stream().map(CompletableFuture::join).collect(Collectors.toList());
        //parallelStream 和CompletableFuture都是1000毫秒左右
//        List list=domeVos.parallelStream().map(domeVo -> domeVo.getCode())
//                .collect(Collectors.toList());
        System.out.println(result.size());
        System.out.println(result.get(4));
        System.out.println(System.currentTimeMillis() - start);

    }


    private final Executor executor= Executors.newFixedThreadPool(8);
    @Test
    public void test3() {
        long start = System.currentTimeMillis();
        List<DomeVo> domeVos = Arrays.asList(new DomeVo(1, true),
                new DomeVo(2, false),
                new DomeVo(3, true),
                new DomeVo(4, true),
                new DomeVo(5, false),
                new DomeVo(6, true),
                new DomeVo(7, true),
                new DomeVo(8, false));
        //使用自定义线程池优化
        //600毫秒左右
        List<CompletableFuture<Integer>> list = domeVos.stream().map(domeVo -> CompletableFuture.supplyAsync(domeVo::getCode,executor))
                .collect(Collectors.toList());
        List<Integer> result = list.stream().map(CompletableFuture::join).collect(Collectors.toList());
//        List list=domeVos.parallelStream().map(domeVo -> domeVo.getCode())
//                .collect(Collectors.toList());
        System.out.println(result.size());
        System.out.println(result.get(4));
        System.out.println(System.currentTimeMillis() - start);

    }
}
