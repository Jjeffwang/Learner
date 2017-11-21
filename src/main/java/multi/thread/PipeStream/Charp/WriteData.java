package multi.thread.PipeStream.Charp;

import java.io.IOException;
import java.io.PipedWriter;

/**
 * Created by ${WangChengYong} on 2017/11/21.
 */
public class WriteData {
    public void writeMethod(PipedWriter writer){
        try {
            writer.write("test");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
