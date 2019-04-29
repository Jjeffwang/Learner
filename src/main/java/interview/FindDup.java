package interview;

import java.util.Arrays;

public class FindDup {


    public static void main(String[] args) {
        int [] arr={0,1,4,5,3,2,1,5,3};
        getRepeateNum(arr);
        Arrays.sort(arr);
    }

    public static void getRepeateNum( int[] num) {
        int NumChange;
        System.out.println("重复数字是：");
        for (int index = 0; index < num.length; index++) {
            while (num[index] != index) {
                if (num[index] == num[num[index]]) {
                    System.out.print(num[index] + " ");
                    break;
                } else {
                    NumChange = num[num[index]];
                    num[num[index]] = num[index];
                    num[index] = NumChange;
                }
            }
        }
    }
}
