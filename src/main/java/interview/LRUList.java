package interview;

/**
 * Created with IDEA
 * author:wang
 * Date:2018/8/29 0029 下午 1:48
 * Description:
 */
public class LRUList<K, V> {

    //容器大小
    private int capacity;
    //节点计数
    private int nodeCount;
    //头结点
    private Node<K, V> head;
    //尾节点
    private Node<K, V> tail;

    public LRUList(int capacity) {
        this.capacity = capacity;
        head = new Node<>();
        head.next = null;
        tail = new Node<>();
        tail.next = head;
        tail.prev = null;
        head.prev = tail;
    }

    public void put(K key, V value) {
        addNode(key, value);
    }

    public V get(K key) {
        Node node = tail;
        for (int i = 0; i < nodeCount; i++) {
            if (node.key == key) {
                moveToHead(node);
                return (V) node.value;
            } else node = node.next;
        }
        return (V) "no value";
    }

    private void moveToHead(Node node) {
        if (node.prev == null) {
            //删除打钱节点增加新节点到头部
            node.next.prev = null;
            tail = node.next;
            nodeCount--;
        }
        if (node.next == null) return;
        if (node.next != null && node.prev != null) {
            //删除打钱节点增加新节点到头部
            node.prev.next = node.next;
        }
        //注意这里需要重新 new 一个对象，不然原本的node 还有着下面的引用，会造成内存溢出。
        node = new Node<>(node.getKey(), node.getValue());
        addHead(node);
    }

    private void addNode(K key, V value) {
        Node node = new Node<>(key, value);
        if (nodeCount == capacity)
            delNode();
        addHead(node);

    }

    private void addHead(Node node) {
        head.next = node;
        node.prev = head;
        node.next = null;
        head = node;
        nodeCount++;
        //如果写入的数据大于2个 就将初始化的头尾结点删除
        if (nodeCount == 2) {
            tail.next.next.prev = null;
            tail = tail.next.next;
        }
    }

    private void delNode() {
        tail.next.prev = null;
        tail = tail.next;
        nodeCount--;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<K, V> node = tail;
        while (node != null) {
            sb.append(node.getKey()).append(":")
                    .append(node.getValue())
                    .append("-->");

            node = node.next;
        }


        return sb.toString();
    }

    private class Node<K, V> {
        private K key;
        private V value;
        Node<K, V> next;
        Node<K, V> prev;

        public Node() {
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {

        LRUList<String, Integer> lruMap = new LRUList(3);
        lruMap.put("1", 1);
        lruMap.put("2", 2);
        lruMap.put("3", 3);
        lruMap.get("3");
        lruMap.get("5");
        System.out.println(lruMap.get("3"));
        System.out.println(lruMap.toString());
        lruMap.put("4", 4);
        System.out.println(lruMap.toString());
        lruMap.put("5", 5);
        System.out.println(lruMap.toString());

    }
}
