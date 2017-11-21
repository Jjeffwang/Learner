package multi.thread.PipeStream.Byte;

import java.io.IOException;
import java.io.PipedOutputStream;

/**
 * Created by ${WangChengYong} on 2017/11/21.
 */
public class WriteData {

    public void writeMethod(PipedOutputStream out) {
        try {
            System.out.println("write:");
            for (int i = 0; i <= 10; i++) {
                String data = "" + (i + 1);
                out.write(data.getBytes());
                System.out.print(data);
            }
            System.out.println("- - - - - - - - - - - - - - - - - ");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
