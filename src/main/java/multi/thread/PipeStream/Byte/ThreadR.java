package multi.thread.PipeStream.Byte;

import java.io.PipedInputStream;

/**
 * Created by ${WangChengYong} on 2017/11/21.
 */
public class ThreadR extends Thread{

    private ReadData readData;
    private PipedInputStream input;

    public ThreadR(ReadData readData,PipedInputStream input){
        this.readData=readData;
        this.input=input;
    }

    @Override
    public void run() {
        readData.readMethod(input);
    }
}
