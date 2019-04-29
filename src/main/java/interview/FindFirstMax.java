package interview;

/**
 * Created with IDEA
 * author:wang
 * Date:2018/8/20 0020 下午 3:58
 * Description:
 */
public class FindFirstMax {

    public static void main(String[] args) {
        int[] numbers = new int[]{13, 22, 34, 66,66,66, 67, 71, 71,75, 88,99};
        int findIndex=find(numbers,22,0,numbers.length-1);
        System.out.println(findIndex);
    }

    public static int find(int[] arr,int x,int low,int high){
        if(arr.length==0){
            return -1;
        }
        if(x<arr[low]||x>arr[high]||low>high){
            return -1;
        }
        int mid=(high+low)/2;
        if(arr[mid]==x){
            return findFirst(arr,x,mid);
        }else if(arr[mid]>x){
            mid=(mid-1)/2;
            return  find(arr,x,low,mid);
        }else {
            mid=(mid+1)/2;
            return find(arr,x,mid,high);
        }
    }

    private static int findFirst(int[] arr,int x,int index){
        while (arr[index]==x){
            index--;
        }
        return index+1;
    }
}
