package StructuresAndAlgorithm.tree;

/**
 * Created by ${WangChengYong} on 2018/3/30.
 */
public class BinarySearchTreeTest {
    public static void main(String[] args) {
        BinarySearchTree<Integer> binarySearchTree=new BinarySearchTree();
        binarySearchTree.insert(3);
        binarySearchTree.insert(2);
        binarySearchTree.insert(10);
        binarySearchTree.insert(12);
        binarySearchTree.insert(8);
        binarySearchTree.insert(6);
        binarySearchTree.insert(7);
        binarySearchTree.insert(9);
        int max=binarySearchTree.findMax();
        System.out.println(max);
        binarySearchTree.printTree();
    }
}
