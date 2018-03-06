package enums;

/**
 * Created by ${WangChengYong} on 2018/1/5.
 */
public class HourlyPayGradeTest {

    public static void main(String[] args) {
        HourlyPayGrade grade = HourlyPayGrade.JOURNEYMAN;

        System.out.println(grade.rate() * 2);
    }
}
