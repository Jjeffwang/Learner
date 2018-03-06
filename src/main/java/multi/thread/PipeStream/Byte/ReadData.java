package multi.thread.PipeStream.Byte;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by ${WangChengYong} on 2017/11/21.
 */
public class ReadData {

    public void readMethod(PipedInputStream input) {
        try {
            System.out.println("input:");
            byte[] bytes = new byte[20];
            int len = input.read(bytes);
            while (len != -1) {
                String data = new String(bytes, 0, len);
                System.out.print(data);
                len = input.read(bytes);
            }
            System.out.println("- - - - - - - - - - - - - - - - - ");
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
