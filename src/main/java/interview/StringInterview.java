package interview;

/**
 * Created with IDEA
 * author:wang
 * Date:2018/8/6 0006 上午 10:46
 * Description:
 */
public class StringInterview {

    public static void main(String[] args) {
        String str="aaabbcceeeedsaaffk";
        StringBuilder stringBuilder=new StringBuilder();
        char[] arr=str.toCharArray();
        int count=1;
        for(int i=0;i<arr.length-1;i++){
            if(arr[i]==arr[i+1]){
                count++;
            }else {
                if(count>1){
                    stringBuilder.append(arr[i]).append(count);
                    count=1;
                }else stringBuilder.append(arr[i]);
            }
            if(arr.length==i+2){
                if(count>1){
                    stringBuilder.append(arr[i]).append(count);
                    count=1;
                }else stringBuilder.append(arr[i+1]);
            }
        }
        System.out.println(stringBuilder.toString());
    }
}
