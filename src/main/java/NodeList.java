public class NodeList<K, V> {

    private int size;
    private Node<K, V> head;
    private Node<K, V> tail;

    public NodeList() {
        size = 0;
        head = null;
        tail = null;
    }

    public void addLast(Node<K, V> node) {
        assert (node != null);
        if (head == null) {
            head = node;
        }
        if (tail != null) {
            tail.safeJoinRight(node);
        }
        tail = node;
        size++;
    }

    public void delete(Node<K, V> node) {
        assert (node != null);
        assert (head != null);
        assert (size > 0);
        if (head == node) {
            head = node.getNext();
        }
        if (tail == node) {
            tail = node.getPrev();
        }
        node.safeDelete();
        size--;
        assert (size >= 0);
    }

    public void clear() {
        size = 0;
        head = null;
        tail = null;
    }

    public int getSize() {
        return size;
    }

    public Node<K, V> getHead() {
        return head;
    }

    public Node<K, V> getTail() {
        return tail;
    }

}
