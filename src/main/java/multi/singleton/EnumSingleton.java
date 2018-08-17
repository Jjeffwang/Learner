package multi.singleton;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with IDEA
 * author:wang
 * Date:2018/8/17 0017 下午 1:44
 * Description:
 */
public enum EnumSingleton {
    INSTANCE;

    private static final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(10000);

    public void put(String message) {

        try {
            queue.put(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String take() {
        try {
            return queue.take();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int size() {
        return queue.size();
    }


    public static void main(String[] args) {
        EnumSingleton.valueOf("INSTANCE");
        System.out.println(EnumSingleton.valueOf("INSTANCE"));
    }

}
