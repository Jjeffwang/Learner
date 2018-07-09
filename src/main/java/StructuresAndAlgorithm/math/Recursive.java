package StructuresAndAlgorithm.math;

/**
 * 递归
 * Created by ${WangChengYong} on 2018/3/28.
 */
public class Recursive {

    public static void main(String[] args) {
//        printOut(888);
        System.out.println(f(2));
    }

    public static void printOut(int n){
        if(n>=10){
            printOut(n/10);
        }else System.out.println(n%10);
    }

    public static int f(int x){
        if(x==0)
            return 0;
        else  return 2*f(x-1)+x*x;
    }
}
