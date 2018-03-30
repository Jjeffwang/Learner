package table;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 把表中的偶数项删除
 * Created by ${WangChengYong} on 2018/3/29.
 */
public class RemoveEvenVer {


    public static void main(String[] args) {
        List<Integer> list = new LinkedList();
        list.add(6);
        list.add(5);
        list.add(1);
        list.add(4);
        list.add(2);
        removeEvenVer3(list);
        for(Integer integer:list){
            System.out.println(integer);
        }

    }

    /**
     * 这种写法对于ArrayList和LinkedList都是 两次的算法中既包含get有包含remove
     *
     * @param list
     */
    public static void removeEvenVer1(List<Integer> list) {
        int i = 0;
        while (i < list.size()) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
            } else i++;
        }
    }

    /**
     * 抛出异常
     * 使用remove是，一个被删除时，由增强for循环所使用的基础迭代器是非法的
     *
     * @param list
     */
    public static void removeEvenVer2(List<Integer> list) {
        for (Integer x : list) {
            if (x % 2 == 0) {
                list.remove(x);
            }
        }
    }

    /**
     * LinkedList remove是一次的 ArrayList是两次的
     * @param list
     */
    public static void removeEvenVer3(List<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() % 2 == 0) {
                iterator.remove();
            }
        }
    }

}
