public interface LRUCache<K, V> extends Iterable<Pair<K, V>> {
    int size();

    boolean isEmpty();

    boolean containsKey(K key);

    V get(K key);

    void put(K key, V value);

    void remove(K key);

    void clear();
}
