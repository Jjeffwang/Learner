package multi.thread.PipeStream.Byte;

import java.io.PipedOutputStream;

/**
 * Created by ${WangChengYong} on 2017/11/21.
 */
public class ThreadW extends Thread {

    private WriteData writeData;
    private PipedOutputStream out;
    public ThreadW( WriteData writeData,PipedOutputStream out){
        this.writeData=writeData;
        this.out=out;
    }

    @Override
    public void run() {
        writeData.writeMethod(out);
    }
}
