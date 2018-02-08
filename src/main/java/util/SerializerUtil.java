package util;

import com.caucho.hessian.io.HessianSerializerInput;
import com.caucho.hessian.io.HessianSerializerOutput;

import java.io.*;

/**
 * Created by ${WangChengYong} on 2018/2/8.
 */
public class SerializerUtil {

    private static <T> Object serializerJava(T t) {

        Object result = null;
        try {
            //定义字节数组输出流
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            //对象输出流
            //Hessian实现
            HessianSerializerOutput outputStream = new HessianSerializerOutput(byteArrayOut);
            //java自带实现
//            ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOut);
            //写入字节输出流，进行序列化
            outputStream.writeObject(t);


            ByteArrayInputStream byteArrayInput = new ByteArrayInputStream(byteArrayOut.toByteArray());
            //执行反序列化，从流中读取对象数据
            HessianSerializerInput inputStream = new HessianSerializerInput(byteArrayInput);
//            ObjectInputStream inputStream = new ObjectInputStream(byteArrayInput);
            result = inputStream.readObject();

            byteArrayOut.close();
            outputStream.close();
            byteArrayInput.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) {
        Person person = new Person("tom", "male");
        Person tom = (Person) serializerJava(person);
        System.out.println(tom.getSex());
    }


    static class Person implements Serializable {
        Person(String name, String sex) {
            this.name = name;
            this.sex = sex;
        }

        private String name;
        private String sex;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
