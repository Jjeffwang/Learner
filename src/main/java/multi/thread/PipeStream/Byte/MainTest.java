package multi.thread.PipeStream.Byte;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by ${WangChengYong} on 2017/11/21.
 */
public class MainTest {

    public static void main(String[] args) {
        try {
            WriteData writeData = new WriteData();
            ReadData readData = new ReadData();
            PipedInputStream input = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream();
            out.connect(input);
            ThreadW threadW=new ThreadW(writeData,out);
            threadW.start();
            Thread.sleep(3000);
            ThreadR threadR=new ThreadR(readData,input);
            threadR.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
