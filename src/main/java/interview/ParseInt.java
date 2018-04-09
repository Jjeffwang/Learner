package interview;

/**
 * Created by ${WangChengYong} on 2018/4/3.
 */
public class ParseInt {

    public static void main(String[] args) {
        String sss = "1234";
        System.out.println(parseInt(sss, 10));
    }

    public static int parseInt(String s, int radix)
            throws NumberFormatException {

        if (s == null) {
            System.out.println("null");
        }
        int i = 0, len = s.length();
        int result = 0;
        if (len > 0) {
            while (i < len) {
                //0在ascii表里的数字是48
                int sum = s.charAt(i++) - 48;
                result *= radix;
                result += sum;
            }
        }
        return result;
    }


    public static long parseLong(String s, int radix)
            throws NumberFormatException {

        if (s == null) {
            System.out.println("null");
        }
        long result = 0;
        int i = 0, len = s.length();
        if (len > 0) {
            while (i < len) {
                //0在ascii表里的数字是48
                int sum = s.charAt(i++) - 48;
                result *= radix;
                result += sum;
            }
        }
        return result;
    }


    public static Long valueOf(String s) {

        long num = parseLong(s, 10);
        return num;
    }
}
