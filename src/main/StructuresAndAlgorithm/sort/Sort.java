package sort;

/**
 * Created by ${WangChengYong} on 2018/4/9.
 */
public class Sort {

    public static void main(String[] args) {
        int[] numbers = new int[]{22, 13, 66, 66, 34, 99, 88, 65, 55, 33, 21, 77, 43};
//        buildMaxHeap(numbers,numbers.length-1);
        mergeSort(numbers);
//        shellSort(numbers);
        for (int number : numbers) {
            System.out.println(number);
        }

    }


    /**
     * 插入排序
     *
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void insertSort(T[] a) {

        int j;
        for (int p = 1; p < a.length; p++) {
            T temp = a[p];
            for (j = p; j > 0 && temp.compareTo(a[j - 1]) < 0; j--)
                a[j] = a[j - 1];
            a[j] = temp;
        }
    }


    /**
     * 希尔排序  插入排序的改进
     *
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void shellSort(T[] a) {
        int j;
        for (int gap = a.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < a.length; i++) {
                T temp = a[i];
                for (j = i; j >= gap && temp.compareTo(a[j - gap]) < 0; j -= gap)
                    a[j] = a[j - gap];
                a[j] = temp;

            }
            for (T number : a) {
                System.out.print(number + "  ");
            }
            System.out.println();

        }
    }

    public static <T> void heapSort(T[] a) {

    }

    private static int leftChild(int i) {
        return 2 * i + 1;
    }


    /**
     * 堆排序
     * 建堆
     * 根据对的跟为最大或者最小通过多次建堆直到只有两个结点得到一个有序的序列
     *
     * @param numbers
     */
    public static void heapSort(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            //建堆
            buildMaxHeap(numbers, numbers.length - 1 - i);
            for (int number : numbers) {
                System.out.print(number + "  ");
            }
            System.out.println();
            swap(numbers, 0, numbers.length - 1 - i);
        }
    }

    public static void mergeSort(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        sort(arr, low, high);

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

    private static void merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int k = 0;
        int i = low;
        int j = mid + 1;
        while (i <= mid && j <= high) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= high) {
            temp[k++] = arr[j++];
        }
        for (int p = 0; p < temp.length; p++) {
            arr[p + low] = temp[p];
        }
    }

    /**
     * 建堆
     *
     * @param data
     * @param lastIndex
     */
    private static void buildMaxHeap(int[] data, int lastIndex) {

        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {

            int k = i;
            //如果当前k节点的子节点存在
            while (k * 2 + 1 <= lastIndex) {
                //k节点的左子节点的索引
                int biggerIndex = 2 * k + 1;
                //如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
                if (biggerIndex < lastIndex) {
                    //比较k结点的叶子结点找到较大的叶子结点
                    if (data[biggerIndex] < data[biggerIndex + 1]) {
                        biggerIndex++;
                    }
                }
                //如果k节点的值小于其较大的子节点的值
                if (data[k] < data[biggerIndex]) {
                    swap(data, k, biggerIndex);
                    k = biggerIndex;
                } else break;
            }
        }
    }

    private static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
}
