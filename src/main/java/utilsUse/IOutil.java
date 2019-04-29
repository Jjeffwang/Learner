package utilsUse;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created with IDEA
 * author:wang
 * Date:2018/7/6 0006 下午 3:44
 * Description:
 */
public class IOutil {

    public static void main(String[] args) {
        InputStream inputStream = null;
        try {
            inputStream=new URL("http://www.baidu.com").openStream();
            String str=IOUtils.toString(inputStream,"UTF-8");
            System.out.println(str);
//            IOUtils.readLines(inputStream,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(inputStream);
        }
    }
}
