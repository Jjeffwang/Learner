package multi.thread.module.signal;

/**
 * Created by jeff on 2017/11/22.
 */
public class StaticObject {

    private static StaticObject staticObject = null;

    private StaticObject() {
    }

    static {
        staticObject = new StaticObject();
    }

    public static StaticObject getInstance() {
        return staticObject;
    }
}
