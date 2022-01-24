public class Node<K, V> {
    private final K key;
    private final V value;

    private Node<K, V> prev;
    private Node<K, V> next;

    public Node(K key, V value, Node<K, V> prev, Node<K, V> next) {
        assert (key != null);
        assert (value != null);
        this.key = key;
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

    public void safeJoinRight(Node<K, V> right) {
        assert (this.next == null);
        assert (right != null && right.prev == null);
        this.next = right;
        right.prev = this;
    }

    public void safeDelete() {
        if (this.prev != null) {
            assert (this.prev.next == this);
            this.prev.next = this.next;
        }
        if (this.next != null) {
            assert (this.next.prev == this);
            this.next.prev = this.prev;
        }
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public Node<K, V> getPrev() {
        return prev;
    }

    public Node<K, V> getNext() {
        return next;
    }
}
