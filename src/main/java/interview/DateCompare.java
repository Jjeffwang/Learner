package interview;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ${WangChengYong} on 2018/2/6.
 */
public class DateCompare {


    public static void main(String[] args) {
        System.out.println(getMonthDiff("2017-08-08", "2018-09-07 14:12:12"));
    }


    private static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    private static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    private static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    private static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    private static int getMonthDiff(String startDate, String endDate) {

        int result = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormatTwo = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date dateOne = dateFormat.parse(startDate);
            Date dateTwo = dateFormatTwo.parse(endDate);
            dateTwo = dateFormat.parse(dateFormat.format(dateTwo));
            int startYear = getYear(dateOne);
            int startMonth = getMonth(dateOne);
            int startDay = getDay(dateOne);
            int endYear = getYear(dateTwo);
            int endMonth = getMonth(dateTwo);
            int endDay = getDay(dateTwo);
            if (startDay > endDay) {
                if (endDay == getDaysOfMonth(getYear(new Date()), 2)) {
                    result = (endYear - startYear) * 12 + endMonth - startMonth;
                } else {
                    result = (endYear - startYear) * 12 + endMonth - startMonth - 1;
                }
            } else {
                result = (endYear - startYear) * 12 + endMonth - startMonth;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
