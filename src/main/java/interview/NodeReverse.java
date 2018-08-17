package interview;

/**
 * Created with IDEA
 * author:wang
 * Date:2018/8/13 0013 上午 11:06
 * Description:
 */
public class NodeReverse {

    public static void main(String[] args) {

        Node node1=new Node(1);
        Node node2=new Node(2);
        Node node3=new Node(3);
        node1.next=node2;
        node2.next=node3;
        Node node=reverse(node1);
        print(node);

    }

    private static Node reverse(Node node){
//        if(node==null){
//            return null;
//        }
        Node prev = null;
        Node now=node;
        while (now != null) {
            Node next = now.next;
            now.next = prev;
            prev = now;
            now = next;
        }

        return prev;
    }

    static class Node {
        int index;
        Node next;

        public Node(int index) {
            this.index = index;
        }
        Node(int index, Node next) {
            this.index = index;
            this.next = next;
        }
    }

    private static void print(Node node) {
        while (node != null) {
            System.out.print(node.index);
            node = node.next;
            if (node != null) {
                System.out.print("->");
            } else {
                System.out.println();
            }
        }
    }
}
