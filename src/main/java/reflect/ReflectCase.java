package reflect;

import java.lang.reflect.Method;

/**
 * Created by ${WangChengYong} on 2018/3/14.
 */
public class ReflectCase {

    public static void main(String[] args) throws Exception {
        System.out.println(Proxy.class);
        Class target= Class.forName("reflect.ReflectCase$Proxy");
        Method method=target.getDeclaredMethod("run");
        System.out.println(method);
//        method.invoke(obj);
    }

    static class Proxy{
        public void run(){
            System.out.println("running!!!");
        }
    }
}
