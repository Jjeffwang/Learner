package table;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by ${WangChengYong} on 2018/3/30.
 */
public class MyLinkedList<T> implements Iterable<T> {

    private int theSize;
    private int modCount;
    private Node<T> beginMarker;
    private Node<T> endMarker;

    public MyLinkedList(){
        doClear();
    }
    public void clear() {
        doClear();
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean add(T t) {
        add(size(), t);
        return true;
    }

    public void add(int index, T t) {
        addBefore(getNode(index,0,size()), t);
    }

    public T get(int index) {
        return getNode(index).data;
    }

    public T set(int index, T newVal) {
        Node<T> node = getNode(index);
        T oldVal = node.data;
        node.data = newVal;
        return oldVal;
    }

    public T remove(int index) {
        return remove(getNode(index));
    }

    /**
     * 头结点指向尾结点
     */
    private void doClear() {
        beginMarker = new Node<T>(null, null, null);
        endMarker = new Node<T>(null, beginMarker, null);
        beginMarker.next = endMarker;
        theSize=0;
        modCount++;
    }

    /**
     * 在P结点前面添加一个结点
     *
     * @param p
     * @param x
     */
    public void addBefore(Node<T> p, T x) {
        Node<T> newNode = new Node<>(x, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;

    }


    private Node<T> getNode(int index) {
        return getNode(index, 0, size() - 1);
    }

    private Node<T> getNode(int index, int lower, int upper) {

        Node<T> p;
        if (index < lower || index > upper) {
            throw new IndexOutOfBoundsException();
        }
        if (index < size() / 2) {
            p = beginMarker.next;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
        } else {
            p = endMarker;
            for (int i = size(); i > index; i--) {
                p = p.prev;
            }
        }
        return p;
    }

    private T remove(Node<T> p) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;
        return p.data;
    }


    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator() ;
    }

    private class LinkedListIterator implements Iterator<T> {

        private Node<T> current = beginMarker.next;
        private int exceptModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public T next() {
            if (modCount != exceptModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        @Override
        public void remove() {
            if (modCount != exceptModCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            MyLinkedList.this.remove(current.prev);
            exceptModCount++;
            okToRemove = false;
        }
    }


    private static class Node<T> {
        public Node(T t, Node<T> p, Node<T> n) {
            data = t;
            prev = p;
            next = n;
        }

        public T data;
        public Node<T> prev;
        public Node<T> next;
    }
}
