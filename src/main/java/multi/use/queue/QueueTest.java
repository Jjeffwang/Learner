package multi.use.queue;

import java.util.HashMap;

/**
 * Created by ${WangChengYong} on 2018/2/6.
 */
public class QueueTest {

    public static void main(String[] args) {
        BoundedQueue boundedQueue = new BoundedQueue(5);
        try {
            boundedQueue.add("1");
            boundedQueue.add("2");
            boundedQueue.add("3");
            boundedQueue.add("4");
            boundedQueue.add("5");
            boundedQueue.remove();
            System.out.println(boundedQueue.get(0));
            System.out.println(boundedQueue.get(1));
            System.out.println(boundedQueue.get(2));
            System.out.println(boundedQueue.get(3));
            System.out.println(boundedQueue.get(4));
            boundedQueue.add(6);
            System.out.println("---------------------------------------");
            System.out.println(boundedQueue.get(0));
            System.out.println(boundedQueue.get(1));
            System.out.println(boundedQueue.get(2));
            System.out.println(boundedQueue.get(3));
            System.out.println(boundedQueue.get(4));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
