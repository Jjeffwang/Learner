package StructuresAndAlgorithm.table;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by ${WangChengYong} on 2018/3/29.
 */
public class MyArrayList<T> implements Iterable<T> {

    private static final int Default_CAPACITY = 10;

    private int theSize;

    private T[] theItems;

    public MyArrayList() {
        doClear();
    }

    public void doClear() {
        theSize = 0;
        ensureCapacity(Default_CAPACITY);
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void trimToSize() {
        ensureCapacity(size());
    }

    public T get(int index) {
        if (index < 0 || index > +size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return theItems[index];
    }

    public T set(int index, T newValue) {
        if (index < 0 || index > +size())
            throw new ArrayIndexOutOfBoundsException();
        T old = theItems[index];
        theItems[index] = newValue;
        return old;
    }

    public void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize) return;
        T[] old = theItems;
        theItems = (T[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            theItems[i] = old[i];
        }
    }

    /**
     * 数组最后一位后面添加
     *
     * @param t
     * @return
     */
    public boolean add(T t) {
        add(size(), t);
        return true;
    }

    /**
     * index后的后移一位
     *
     * @param index
     * @param t
     */
    public void add(int index, T t) {
        if (theItems.length == size())
            ensureCapacity(size() * 2 + 1);
        for (int i = theSize; i > index; i--)
            theItems[i] = theItems[i - 1];
        theItems[index] = t;


        theSize++;
    }

    public T remove(int index) {
        T removeItem = theItems[index];
        for (int i = index; i < size() - 1; i++)
            theItems[i] = theItems[i + 1];

        theSize--;
        return removeItem;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            return theItems[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }
}
