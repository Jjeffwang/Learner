package math;

/**
 * Created by ${WangChengYong} on 2018/3/28.
 */
public class MaxSequence {


    public static void main(String[] args) {
        int[] arr = {-2, 11, -4, 13, -5, -2};
        System.out.println(MaxSubSum4(arr));
    }

    /**
     * O(n*n*n)
     *
     * @param arr
     * @return
     */
    public static int MaxSubSum1(int[] arr) {
        int maxSum = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int thisSum = 0;
                for (int k = i; k <= j; k++) {
                    thisSum += arr[k];
                }
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }


    /**
     * O(n*n)
     *
     * @param arr
     * @return
     */
    public static int MaxSubSum2(int[] arr) {
        int maxSum = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int thisSum = 0;
            for (int j = i; j < len; j++) {
                thisSum += arr[j];
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }


    /**
     * O(NlogN)
     *
     * @param arr
     * @return
     */
    public static int MaxSubSum3(int[] arr) {
        return MaxSubSum(arr, 0, arr.length - 1);
    }

    /**
     * O(N)
     * @param arr
     * @return
     */
    public static int MaxSubSum4(int[] arr) {
        int maxSum = 0;
        int thisSum = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            thisSum += arr[i];
            if (thisSum > maxSum) maxSum = thisSum;
            //增加一次thisSum<0的判断减少一次嵌套for循环,没有了重复计算
            else if (thisSum < 0) thisSum = 0;
        }
        return maxSum;
    }


    public static int MaxSubSum(int[] arr, int left, int right) {
        if (left == right) {
            if (arr[left] > 0)
                return arr[left];
            else return 0;
        }
        int center = (left + right) / 2;
        System.out.println(center);
        int maxLeftSum = MaxSubSum(arr, left, center);
        int maxRightSum = MaxSubSum(arr, center + 1, right);

        int leftSum = 0, leftSumBorder = 0;
        for (int i = center; i >= left; i--) {
            leftSumBorder += arr[i];
            if (leftSumBorder > leftSum) leftSum = leftSumBorder;
        }

        int rightSum = 0, rightSumBorder = 0;
        for (int i = center + 1; i <= right; i++) {
            rightSumBorder += arr[i];
            if (rightSumBorder > rightSum) rightSum = rightSumBorder;
        }
        return max3(maxLeftSum, maxRightSum, leftSum + rightSum);
    }


    public static int max3(int first, int second, int third) {
        int temp;
        if (first > second)
            temp = first;
        else temp = second;
        if (temp > third) return temp;
        else return third;
    }

}
