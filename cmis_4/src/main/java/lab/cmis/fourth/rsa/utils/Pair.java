package lab.cmis.fourth.rsa.utils;

public class Pair<K, V> {
    private final K key;
    private final V value;

    public Pair(final K key, final V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return  value;
    }
}
