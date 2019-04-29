package java8.completableFuture.demo;

import java.util.Random;

public class Shop {

    private String name;

    public Shop(String name) {
        this.name = name;
    }

    public String getPrice(String product){
        Random random = new Random();
        dely();
        double price= calculatePrice(product);
        Discount.Code code=Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s",product,price,code);
    }

    private static double calculatePrice(String product) {
        Random random = new Random();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }
    private void dely() {
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
