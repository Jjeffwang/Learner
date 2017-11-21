package multi.thread.block;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeff on 2017/11/15.
 */
public class MyOneList {

    private List list = new ArrayList();

    synchronized public void add(String data) {
        list.add(data);
    }

    synchronized int getSize() {
        return list.size();
    }

}
