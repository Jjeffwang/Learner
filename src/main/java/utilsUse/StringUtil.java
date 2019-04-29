package utilsUse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created with IDEA
 * author:wang
 * Date:2018/7/6 0006 下午 4:12
 * Description:
 */
public class StringUtil {

    public static void main(String[] args) {
        String str = "<br my new test";
        StringUtils.equals(str, "test");
        StringUtils.contains("my", "test");
        str=StringUtils.join(new String[]{str, " demo"}, "-");


        //html标签转义
        str=StringEscapeUtils.escapeHtml4(str);
        System.out.println(str);
    }
}
