class HashNode<K, V> {
    K key;
    V value;
    final int hashCode;
    HashNode<K, V> next;

    public HashNode(K key, V value, int hashCode) {
        this.key = key;
        this.value = value;
        this.hashCode = hashCode;
    }
}
