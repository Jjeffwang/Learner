package reflect;

import java.lang.reflect.Method;

/**
 * Created by ${WangChengYong} on 2018/3/14.
 */
public class ReflectCase {

    public static void main(String[] args) throws Exception {
        System.out.println(Proxy.class);
        Class target= Class.forName("reflect.Proxy");
        Method method=target.getDeclaredMethod("run");
        Object obj=target.newInstance();
        System.out.println(method);
        method.invoke(obj);
    }
}
