package StructuresAndAlgorithm.tree;

/**
 * Created with IDEA
 * author:wang
 * Date:2018/8/27 0027 下午 5:32
 * Description:
 */
public class BinaryTree<T extends Comparable<? super T>> {


    private Node<T> root;

    public BinaryTree() {
        root = null;
    }

    public void insert(T t) {
        root = insert(t, root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public Node<T> insert(T t, Node<T> node) {
        if (node == null)
            return new Node<T>(t, null, null);
        int compareResult = t.compareTo(node.element);
        if (compareResult < 0) {
            node.left = insert(t, node.left);
        }
        if (compareResult > 0) {
            node.right = insert(t, node.right);
        } else ;
        return node;
    }

    public void printTree() {
        if (isEmpty())
            System.out.println("Empty tree");
        else
            printTree(root);
    }

    private void printTree(Node<T> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }


    private static class Node<T> {
        T element;
        Node<T> left;
        Node<T> right;

        Node(T theElement) {
            this(theElement, null, null);
        }

        Node(T theElement, Node<T> lt, Node<T> rt) {
            element = theElement;
            lt = left;
            rt = right;
        }
    }
}
