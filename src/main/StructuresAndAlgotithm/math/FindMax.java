package math;

import java.util.Comparator;

/**
 * Created by ${WangChengYong} on 2018/3/28.
 */
public class FindMax {

    public static void main(String[] args) {
        String aaa = "23223";
        char ccc = aaa.charAt(1);
        System.out.println(ccc);
        Long.valueOf(aaa);
        String[] arr = {"ZEBRA", "tom", "cookie"};
        System.out.println(findMax(arr, new CaseInstensiveCompare()));
    }

    public static <AnyType> AnyType findMax(AnyType[] arr, Comparator<? super AnyType> comparator) {
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (comparator.compare(arr[i], arr[maxIndex]) > 0) {
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }

    static class CaseInstensiveCompare implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return o1.compareToIgnoreCase(o2);
        }
    }
}
