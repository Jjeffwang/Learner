package StructuresAndAlgorithm.table;

import java.util.Iterator;

/**
 * Created by ${WangChengYong} on 2018/3/30.
 */
public class MyLinkedListTest {

    public static void main(String[] args) {
        MyLinkedList<Integer> linkedList=new MyLinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.remove(3);
        Iterator iterator=linkedList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
