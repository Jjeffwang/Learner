package utilsUse;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created with IDEA
 * author:wang
 * Date:2018/7/6 0006 下午 3:55
 * Description:
 */
public class FileUtil {

    public static void main(String[] args) {
        File file=new File("E:/test.txt");
        try {
            List lines=FileUtils.readLines(file,"UTF-8");
            System.out.println(lines.get(0));
            byte[] fileBytes=FileUtils.readFileToByteArray(file);
            String fileStr=FileUtils.readFileToString(file);
            System.out.println(fileStr);
            FileUtils.writeByteArrayToFile(file,fileBytes);
            FileUtils.writeStringToFile(file,"new demo",true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
