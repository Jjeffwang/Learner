package java8.completableFuture.demo;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class MainTest {

    private final Executor executor = Executors.newFixedThreadPool(5);

    @Test
    public void test1() {
        long start = System.currentTimeMillis();
        List<Shop> shops = Arrays.asList(new Shop("bestPrice"),
                new Shop("firstPrice"),
                new Shop("secondPrice"),
                new Shop("thirdPrice"),
                new Shop("forthPrice"));
        shops.stream().map(shop -> shop.getPrice("one"))
                .map(Quote::parse)
                .map(Discount::applyDiscount).collect(Collectors.toList());
        System.out.println(shops.size());
        System.out.println(System.currentTimeMillis() - start);
        //10127
    }

    @Test
    public void test2() {
        long start = System.currentTimeMillis();
        List<Shop> shops = Arrays.asList(new Shop("bestPrice"),
                new Shop("firstPrice"),
                new Shop("secondPrice"),
                new Shop("thirdPrice"),
                new Shop("forthPrice"));
        shops.parallelStream().map(shop -> shop.getPrice("one"))
                .map(Quote::parse)
                .map(Discount::applyDiscount).collect(Collectors.toList());
        System.out.println(shops.size());
        System.out.println(System.currentTimeMillis() - start);
        //4127
    }

    @Test
    public void test3() {
        long start = System.currentTimeMillis();
        List<Shop> shops = Arrays.asList(new Shop("bestPrice"),
                new Shop("firstPrice"),
                new Shop("secondPrice"),
                new Shop("thirdPrice"),
                new Shop("forthPrice"));
        List<CompletableFuture<String>> list = shops.stream()
                //Stream<CompletableFuture<String>>
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice("one"), executor))
                //Stream<CompletableFuture<Quote>> thenApply转换的是泛型中的类型，是同一个CompletableFuture
                .map(str -> str.thenApply(Quote::parse))
                //Stream<CompletableFuture<Quote>> thenCompos用来连接两个，生成新的CompletableFuture
                .map(quote -> quote.thenCompose(quote1 -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote1), executor)))
                .collect(Collectors.toList());


//        List<CompletableFuture<CompletableFuture<String>>> list2 = shops.stream()
//                //Stream<CompletableFuture<String>>
//                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice("one"), executor))
//                //Stream<CompletableFuture<Quote>> thenApply转换的是泛型中的类型，是同一个CompletableFuture
//                .map(str -> str.thenApply(Quote::parse))
//                //Stream<CompletableFuture<Quote>> thenCompos用来连接两个，生成新的CompletableFuture
//                .map(quote -> quote.thenApply(quote1 -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote1), executor)))
//                .collect(Collectors.toList());


        List<String> result = list.stream().map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(result.size());
        System.out.println(System.currentTimeMillis() - start);
        //2145
    }


    @Test
    public void test4() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        List<Shop> shops = Arrays.asList(new Shop("bestPrice"),
                new Shop("firstPrice"),
                new Shop("secondPrice"),
                new Shop("thirdPrice"),
                new Shop("forthPrice"));
        List<CompletableFuture<String>> list = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice("one"), executor))
                .map(str -> str.thenApply(Quote::parse))
                .map(quote -> quote.thenCompose(quote1 -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote1), executor)))
                .collect(Collectors.toList());
        CompletableFuture[] completableFutures = list.toArray(new CompletableFuture[0]);
        CompletableFuture.anyOf(completableFutures).join();
        CompletableFuture future= completableFutures[0];
        String result= (String) future.get();
        System.out.println(result);
        System.out.println(System.currentTimeMillis() - start);
        //2120
    }
}
