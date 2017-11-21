package multi.thread.p_r;

/**
 * Created by ${WangChengYong} on 2017/11/21.
 */
public class ThreadP extends Thread {

    private Productor productor;

    public ThreadP(Productor productor) {
        this.productor = productor;
    }

    @Override
    public void run() {
        while (true) {
            productor.setValue();
        }
    }
}
