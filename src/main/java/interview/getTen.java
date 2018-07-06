package interview;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by ${WangChengYong} on 2018/4/25.
 */
public class getTen {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        HashMap<Integer, Integer> map = getArr(arr);
        for (Integer value : map.values()) {
            System.out.println(value);
        }
    }

    private static HashMap<Integer, Integer> getArr(int[] arr) {
//        int[] result = new int[10];
        int resultLen = 10;
        HashMap<Integer, Integer> hashMap = new HashMap();
        for (int i = 0; hashMap.size() < resultLen; i++) {
            Random random = new Random();
            int temp = random.nextInt(20);
            hashMap.put(arr[temp], i);
        }

        return hashMap;
    }
}
