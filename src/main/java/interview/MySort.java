package interview;

/**
 * Created with IDEA
 * author:wang
 * Date:2018/8/13 0013 上午 9:54
 * Description:
 */
public class MySort {


    public static void main(String[] args) {
        int[] numbers = new int[]{22, 13, 66, 66, 34, 99, 88, 65, 55, 33, 21, 77, 43};
        quick(numbers);
        for (int number : numbers) {
            System.out.println(number);
        }
    }

    private static void bubbleSort(int[] numbers) {
        int temp;
        for (int i = 0; i < numbers.length; i++) {

            for (int j = 0; j < numbers.length - i - 1; j++) {
                if (numbers[j] < numbers[j + 1]) {
                    temp = numbers[j + 1];
                    numbers[j + 1] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
    }

    public static void mergeSort(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;
        sort(numbers, low, high);
    }

    private static int[] sort(int[] arr, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {

            sort(arr, low, mid);

            sort(arr, mid + 1, high);

            merge(arr, low, mid, high);
        }
        return arr;
    }

    public static void merge(int[] numbers, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int k = 0;
        int i = low;
        int j = mid + 1;
        while (i <= mid && j <= high) {
            if (numbers[i] < numbers[j]) {
                temp[k++] = numbers[i++];
            } else temp[k++] = numbers[j++];
        }
        while (i <= mid) {
            temp[k++] = numbers[i++];
        }
        while (j <= high) {
            temp[k++] = numbers[j++];
        }
        for (int p = 0; p < temp.length; p++) {
            numbers[p + low] = temp[p];
        }
    }

    public static void quick(int[] numbers) {
        printArr(numbers);
        quick(numbers, 0, numbers.length - 1);
    }

    public static void quick(int[] arr, int low, int high) {
        int i = low;
        int j = high;
        if (low > high) {
            return;
        }
        int mid = (high + low) / 2;
        int temp = getMid(low, mid, high, arr)[low];
//        int temp = arr[low];
        while (i < j) {
            while (i < j && temp < arr[j]) {
                j--;
            }
            if (i < j) {
                arr[i++] = arr[j];
            }
            while (i < j && temp >= arr[i]) {
                i++;
            }
            if (i < j) {
                arr[j--] = arr[i];
            }
        }
        arr[i] = temp;
        printArr(arr);
        quick(arr, low, i - 1);
        quick(arr, i + 1, high);
    }

    private static int[] getMid(int first, int second, int third, int arr[]) {
        int temp;
        if (arr[first] < arr[second]) {
            if (arr[second] < arr[third]) {
                temp = arr[first];
                arr[second] = arr[first];
                arr[first] = temp;
            }
        } else {
            if (arr[second] < arr[third]) {
                temp = arr[third];
                arr[third] = arr[first];
                arr[first] = temp;

            }
        }
        return arr;
    }
//    public static void quick(int[] numbers, int low, int high) {
//
//        int i = low;
//        int j = high;
//        if (low > high)
//            return;
//        int index = numbers[low];
//        while (i < j) {
//            while (i < j && index < numbers[j])
//                j--;
//            if (i < j) {
//                numbers[i++] = numbers[j];
//            }
//            while (i < j && index >= numbers[i]) {
//                i++;
//            }
//            if (i < j) {
//                numbers[j--] = numbers[i];
//            }
//        }
//        numbers[i] = index;
//        quick(numbers, low, i - 1);
//        quick(numbers, i + 1, high);
//    }


    private static void printArr(int[] arr) {
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

}
