package interview;

/**
 * Created by ${WangChengYong} on 2018/3/12.
 */
public class ArrComare {

    public static int[] minSum(int[] arr1, int[] arr2) {

        int len = arr1.length;
        int sum;
        int[] result = new int[len];
        int[] minResult = new int[len];
        for (int i = 0; i < len; i++) {

            for (int j = 0; j < len; j++) {
                sum = arr1[i] + arr2[j];
                result[j] = sum;
            }
            int reuslt = getMin(result);
            minResult[i] = reuslt;
        }

        return minResult;
    }

    private static int getMin(int[] arr) {
        int len = arr.length;
        int temp = 0;
        for (int i = 0; i < len - 1; i++) {
            if (arr[i] < arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;

            }
        }
        return arr[len - 1];
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{2, 1, 3, 7, 7, 9};
        int[] arr2 = new int[]{4, 5, 3, 8, 1, 3};
        int[] result = minSum(arr1, arr2);
        int len = result.length;
        for (int i = 0; i < len; i++) {
            System.out.println(result[i]);
        }
        System.out.println("--------------------------------------------");
        System.out.println(getMin(arr1));
        System.out.println(getMin(arr2));

    }
}
