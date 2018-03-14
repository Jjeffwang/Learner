package Algorithm;

/**
 * 冒泡、快速：
 * 先做第一次排序，后循环第一次排序
 * http://www.cnblogs.com/0201zcr/p/4763806.html
 * 选择
 * http://www.cnblogs.com/0201zcr/p/4764427.html
 * Created by ${WangChengYong} on 2017/10/9.
 */
public class BubbleSort {


    public static void main(String[] args) {

        int[] numbers = new int[]{22, 13, 66, 66, 34, 99, 88, 65};
//        bubbleSort(numbers);s
//        getMiddle(numbers, 0, 7);
//        quick(numbers);
//        selectSort(numbers);
//        insertSort(numbers);
//        sort(numbers, 0, 7);
//        buildMaxHeap(numbers,numbers.length-1);
//        for (int number : numbers) {
//            System.out.println(number);
//        }


        int arrayLength = numbers.length;
        for (int i = 0; i < arrayLength - 1; i++) {
            //建堆
            buildMaxHeap(numbers, arrayLength - 1 - i);
            //交换堆顶和最后一个元素
            swap(numbers, 0, arrayLength - 1 - i);
//            System.out.println(Arrays.toString(numbers));
        }
        for (int number : numbers) {
            System.out.println(number);
        }

        System.out.println("-------------------------");
        int result=halfSearch(numbers,22);
        System.out.println(result);
        System.out.println(numbers[result]);

    }


    /**
     * 冒泡排序
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
     * 针对所有的元素重复以上的步骤，除了最后一个。
     * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     *
     * @param numbers 需要排序的整型数组
     */
    private static void bubbleSort(int[] numbers) {
        int temp;
        int size = numbers.length;
        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size - i - 1; j++) {

                if (numbers[j] > numbers[j + 1]) {
                    temp = numbers[j + 1];
                    numbers[j + 1] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
    }

    /**
     * 快速排序
     *
     * @param numbers 带排序数组
     */
    private static void quick(int[] numbers) {
        if (numbers.length > 0)   //查看数组是否为空
        {
            quickSort(numbers, 0, numbers.length - 1);
        }
    }


    /**
     * 选择排序算法
     * 在未排序序列中找到最小元素，存放到排序序列的起始位置
     * 再从剩余未排序元素中继续寻找最小元素，然后放到排序序列末尾。
     * 以此类推，直到所有元素均排序完毕。
     *
     * @param numbers
     */
    private static void selectSort(int[] numbers) {
        int size = numbers.length;
        int temp;
        for (int i = 0; i < size; i++) {
            int k = i;
            for (int j = i + 1; j < size; j++) {
                if (numbers[j] < numbers[k]) {
                    k = j;
                }
            }
//            for (int j = size - 1; j > i; j--) {
//                if (numbers[j] < numbers[k]) {
//                    k=j;
//                }
//            }
            temp = numbers[i];
            numbers[i] = numbers[k];
            numbers[k] = temp;
        }
    }


    /**
     * 插入排序
     * <p>
     * 从第一个元素开始，该元素可以认为已经被排序
     * 取出下一个元素，在已经排序的元素序列中从后向前扫描
     * 如果该元素（已排序）大于新元素，将该元素移到下一位置
     * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * 将新元素插入到该位置中
     * 重复步骤2
     *
     * @param numbers 待排序数组
     */


    private static void insertSort(int[] numbers) {

        int size = numbers.length;
        int temp;
        int j;
        for (int i = 0; i < size; i++) {
            temp = numbers[i];
            for (j = i; 0 < j; j--) {
                if (temp < numbers[j - 1]) {
                    numbers[j] = numbers[j - 1];
                    numbers[j - 1] = temp;

                }
            }
        }
    }


    /**
     * 归并排序
     * 简介:将两个（或两个以上）有序表合并成一个新的有序表 即把待排序序列分为若干个子序列，每个子序列是有序的。然后再把有序子序列合并为整体有序序列
     * 时间复杂度为O(nlogn)
     * 稳定排序方式
     *
     * @param nums 待排序数组
     * @return 输出有序数组
     */
    private static int[] sort(int[] nums, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            // 左边
            sort(nums, low, mid);
            // 右边
            sort(nums, mid + 1, high);
            // 左右归并
            merge(nums, low, mid, high);
        }
        return nums;
    }


    /**
     * 堆排序
     * 初始时把要排序的数的序列看作是一棵顺序存储的二叉树，
     * 调整它们的存储序，使之成为一个 堆，这时堆的根节点的数最大。
     * 然后将根节点与堆的最后一个节点交换。然后对前面(n-1)个数重新调整使之成为堆。
     * 依此类推，直到只有两个节点的堆，并对 它们作交换，最后得到有n个节点的有序序列。
     * 从算法描述来看，堆排序需要两个过程，一是建立堆，二是堆顶与堆的最后一个元素交换位置。
     * 所以堆排序有两个函数组成。一是建堆的渗透函数，二是反复调用渗透函数实现排序的函数。
     *
     * @param data
     * @param lastIndex
     */
    public static void buildMaxHeap(int[] data, int lastIndex) {
        //从lastIndex处节点（最后一个节点）的父节点开始
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            //k保存正在判断的节点
            int k = i;
            //如果当前k节点的子节点存在
            while (k * 2 + 1 <= lastIndex) {
                //k节点的左子节点的索引
                int biggerIndex = 2 * k + 1;
                //如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
                if (biggerIndex < lastIndex) {
                    //若果右子节点的值较大
                    if (data[biggerIndex] < data[biggerIndex + 1]) {
                        //biggerIndex总是记录较大子节点的索引
                        biggerIndex++;
                    }
                }
                //如果k节点的值小于其较大的子节点的值
                if (data[k] < data[biggerIndex]) {
                    //交换他们
                    swap(data, k, biggerIndex);
                    //将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }

    //交换
    private static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }


    /**
     * 将数组中low到high位置的数进行排序
     *
     * @param nums 待排序数组
     * @param low  待排的开始位置
     * @param mid  待排中间位置
     * @param high 待排结束位置
     */
    private static void merge(int[] nums, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;// 左指针
        int j = mid + 1;// 右指针
        int k = 0;

        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (nums[i] < nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = nums[i++];
        }

        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = nums[j++];
        }

        // 把新数组中的数覆盖nums数组
        for (int k2 = 0; k2 < temp.length; k2++) {
            nums[k2 + low] = temp[k2];
        }
    }


    /**
     * 查找出中轴（默认是最低位low）的在numbers数组排序后所在位置
     *
     * @param numbers 带查找数组
     * @param low     开始位置
     * @param high    结束位置
     * @return 中轴所在位置
     */
    private static int getMiddle(int[] numbers, int low, int high) {
        int temp = numbers[low]; //数组的第一个作为中轴
        while (low < high) {
            while (low < high && numbers[high] > temp) {
                high--;
            }
            numbers[low] = numbers[high];//比中轴小的记录移到低端
            while (low < high && numbers[low] < temp) {
                low++;
            }
            numbers[high] = numbers[low]; //比中轴大的记录移到高端
        }
        numbers[low] = temp; //中轴记录到尾
        return low; // 返回中轴的位置
    }


    private static void quickSort(int[] numbers, int low, int high) {
        if (low < high) {
            int middle = getMiddle(numbers, low, high); //将numbers数组进行一分为二
            quickSort(numbers, low, middle - 1);   //对低字段表进行递归排序
            quickSort(numbers, middle + 1, high); //对高字段表进行递归排序
        }

    }

    /**
     * 二分查找，默认数组是升序
     * @param nums
     * @param searchNum
     * @return
     */
    private static int halfSearch(int[] nums,int searchNum){
        int first=0;
        int end=nums.length;
        int count=0;
        for(int i=0;i<end;i++){
            count++;
            int temp=(end-first)/2;
            if(searchNum==nums[temp]){
                System.out.println("查询次数为："+count);
                return temp;
            }
            if(searchNum<nums[temp]){
                end=temp-1;
            }else {
                first=temp+1;
            }
        }
        return 0;
    }
}
