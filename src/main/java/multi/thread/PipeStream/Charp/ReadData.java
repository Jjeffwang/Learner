package multi.thread.PipeStream.Charp;

import java.io.IOException;
import java.io.PipedReader;

/**
 * Created by ${WangChengYong} on 2017/11/21.
 */
public class ReadData {

    public void readMethod(PipedReader reader) {
        try {
            char[] byteArray = new char[20];
            int len = reader.read(byteArray);
            while (len!=-1){
                String data=new String(byteArray,0,len);
                len=reader.read(byteArray);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
