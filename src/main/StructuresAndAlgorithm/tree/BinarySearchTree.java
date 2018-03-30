package tree;

import org.apache.commons.collections.BufferUnderflowException;

/**
 * Created by ${WangChengYong} on 2018/3/30.
 */
public class BinarySearchTree<T extends Comparable<? super T>> {


    private BinaryNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T t) {
        return contains(t, root);
    }

    public T findMin() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        return findMin(root).element;
    }

    public T findMax() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        return findMax(root).element;
    }

    public void insert(T t) {
        root = insert(t, root);
    }

    public void remove(T t) {
        root = remove(t, root);
    }

    public void printTree() {
        if (isEmpty())
            System.out.println("Empty tree");
        else
            printTree(root);
    }

    private boolean contains(T x, BinaryNode<T> node) {
        if (node == null) return false;
        int compareResult = x.compareTo(node.element);
        if (compareResult < 0)
            return contains(x, node.left);
        else if (compareResult > 0)
            return contains(x, node.right);
        else return true;
    }

    private BinaryNode<T> findMin(BinaryNode<T> t) {
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        return findMin(t.left);
    }

    private BinaryNode<T> findMax(BinaryNode<T> t) {
        if (t != null)
            while (t.right != null)
                t = t.right;
        return t;

    }

    private BinaryNode<T> insert(T t, BinaryNode<T> node) {
        if (node == null)
            return new BinaryNode<T>(t, null, null);
        int compareResult = t.compareTo(node.element);
        if (compareResult < 0)
            node.left = insert(t, node.left);
        else if (compareResult > 0)
            node.right = insert(t, node.right);
        else ;//duplicate; do nothing
        return node;
    }

    private void printTree(BinaryNode<T> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    private BinaryNode<T> remove(T t, BinaryNode<T> node) {
        if (node == null)
            return node;
        int compareResult = t.compareTo(node.element);
        if (compareResult < 0)
            remove(t, node.left);
        else if (compareResult > 0)
            remove(t, node.right);
        else if (node.left != null && node.right != null) {
            node.element = findMin(node.right).element;
            node.right = remove(node.element, node.right);
        } else
            node = (node.left != null) ? node.left : node.right;
        return node;
    }

    private static class BinaryNode<T> {

        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;

        BinaryNode(T theElement) {
            this(theElement, null, null);
        }

        BinaryNode(T theElement, BinaryNode<T> lt, BinaryNode<T> rt) {
            element = theElement;
            lt = left;
            rt = right;
        }
    }
}
