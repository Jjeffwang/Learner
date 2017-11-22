package multi.thread.module.signal;

import java.io.Serializable;

/**
 * Created by jeff on 2017/11/22.
 */
public class MyStaticObject implements Serializable{

    private static class  MyStaticObjectHander{
        private static MyStaticObject myStaticObject=new MyStaticObject();
    }

    public static MyStaticObject getInstance(){
        return MyStaticObjectHander.myStaticObject;
    }

//    protected Object readRe
}
