import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LRUCacheImpl<K, V> implements LRUCache<K, V> {

    private final HashMap<K, Node<K, V>> cache;
    private final NodeList<K, V> nodes;
    private final int maxSize;

    private void assertSizeCorrect() {
        assert (cache.size() <= maxSize);
        assert (cache.size() == nodes.getSize());
    }

    /**
     * main constructor
     *
     * @param maxSize maximum number of items in the cache, maxSize >= 1
     */
    public LRUCacheImpl(int maxSize) {
        this.cache = new HashMap<>();
        this.nodes = new NodeList<>();
        this.maxSize = maxSize;
        assert (maxSize >= 1);
        assertSizeCorrect();
    }

    /**
     * add or update item in cache without size check
     *
     * @param key   non-null key
     * @param value non-null value
     */
    private void addOrUpdate(K key, V value) {
        assert (key != null);
        assert (value != null);
        deleteIfExists(key);
        assert (!cache.containsKey(key));
        Node<K, V> node = new Node<>(key, value, null, null);
        nodes.addLast(node);
        cache.put(key, node);
        assert (nodes.getHead() != null);
        assert (nodes.getTail() != null);
        assertSizeCorrect();
    }

    /**
     * removes an item from the cache without size check
     *
     * @param key non-null key
     */
    private void deleteIfExists(K key) {
        assert (key != null);
        if (cache.containsKey(key)) {
            Node<K, V> node = cache.get(key);
            assert (node != null);
            nodes.delete(node);
            cache.remove(key);
        }
        assertSizeCorrect();
    }

    /**
     * returns the number of items in the cache
     *
     * @return size
     */
    @Override
    public int size() {
        assertSizeCorrect();
        return cache.size();
    }

    /**
     * checks if the cache is empty
     *
     * @return true or false
     */
    @Override
    public boolean isEmpty() {
        assertSizeCorrect();
        return cache.isEmpty();
    }

    /**
     * checks if the cache contains an element
     *
     * @param key non-null key
     * @return true or false
     */
    @Override
    public boolean containsKey(K key) {
        assert (key != null);
        assertSizeCorrect();
        return cache.containsKey(key);
    }

    /**
     * get item from cache
     *
     * @param key non-null key
     * @return value or null
     */
    @Override
    public V get(K key) {
        assert (key != null);
        V value = null;
        if (cache.containsKey(key)) {
            Node<K, V> node = cache.get(key);
            assert (node != null);
            value = node.getValue();
            addOrUpdate(key, value);
        }
        return value;
    }

    /**
     * put new item into cache
     *
     * @param key   non-null key
     * @param value non-null value
     */
    @Override
    public void put(K key, V value) {
        assert (key != null);
        assert (value != null);
        int currSize = size();
        if (currSize == maxSize) {
            assert (currSize >= 1);
            assert (nodes.getHead() != null);
            deleteIfExists(nodes.getHead().getKey());
        }
        addOrUpdate(key, value);
        int newSize = size();
        assert ((currSize == maxSize && newSize == maxSize) || (currSize + 1 == newSize));
    }

    /**
     * removes an item from the cache
     *
     * @param key non-null key
     */
    @Override
    public void remove(K key) {
        assert (key != null);
        int currSize = size();
        deleteIfExists(key);
        int newSize = size();
        assert (currSize - 1 == newSize || currSize == newSize);
    }

    /**
     * completely clears the collection
     * <p>
     * sets size to 0
     */
    @Override
    public void clear() {
        cache.clear();
        nodes.clear();
        assert (nodes.getHead() == null);
        assert (nodes.getTail() == null);
        int currSize = size();
        assert (currSize == 0);
    }

    /**
     * implements an iterable interface
     *
     * @return iterator
     */
    @Override
    public Iterator<Pair<K, V>> iterator() {
        return new Iterator<>() {
            private Node<K, V> currNode = nodes.getHead();

            @Override
            public boolean hasNext() {
                return (currNode != null);
            }

            @Override
            public Pair<K, V> next() {
                if (currNode == null) {
                    throw new NoSuchElementException();
                }
                Pair<K, V> result = new Pair<>(currNode.getKey(), currNode.getValue());
                currNode = currNode.getNext();
                return result;
            }
        };
    }
}
