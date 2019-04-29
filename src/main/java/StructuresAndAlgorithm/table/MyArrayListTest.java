package StructuresAndAlgorithm.table;

import java.util.Iterator;

/**
 * Created by ${WangChengYong} on 2018/3/29.
 */
public class MyArrayListTest {

    public static void main(String[] args) {
        MyArrayList<Integer> arrayList=new MyArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(7);
        arrayList.add(8);
        arrayList.add(9);
        arrayList.add(10);
        arrayList.add(11);
        arrayList.add(12);
        Iterator iterator=arrayList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("--------------------------");
        System.out.println(arrayList.size());
    }
}
