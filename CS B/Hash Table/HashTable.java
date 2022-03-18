import java.util.ArrayList;
import java.util.Objects;

public class HashTable<K, V> {
    private ArrayList<HashNode<K, V>> arr;
    private int space;
    private int used;

    public HashTable() {
        arr = new ArrayList<>();
        space = 10;
        used = 0;

        // ? fill empty array
        for (int i = 0; i < space; i++) {
            arr.add(null);
        }
    }

    public int getUsed() {
        return used;
    }

    private final int hashCode(K key) {
        return Objects.hashCode(key);
    }

    // ? find index in array given key
    private int getIndex(K key) {
        int hashCode = hashCode(key);
        int index = hashCode % space;
        index = index < 0 ? index * -1 : index; // * get the absoloute value of the index (coulud be negative)
        return index;
    }

    // ? removes key from hash table
    public V remove(K key) {
        int index = getIndex(key);
        HashNode<K, V> head = arr.get(index); // * head of linked list at index
        HashNode<K, V> prev = null; // * need to store previous node to remove

        while (head != null) {
            if (head.key.equals(key))
                break;
            prev = head;
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        used--;

        if (prev != null) {
            prev.next = head.next;
        } else {
            arr.set(index, head.next);
        }
        return head.value;
    }

    // ? get value of a key from hash table
    public V get(K key) {
        int index = getIndex(key);
        HashNode<K, V> head = arr.get(index);

        while (head != null) {
            if (head.key.equals(key))
                return head.value;
            head = head.next;
        }

        return null;
    }

    // ? add a key value pair to the hash table
    public void add(K key, V value) {
        int index = getIndex(key);
        int hashCode = hashCode(key);
        HashNode<K, V> head = arr.get(index);

        while (head != null) { // * check if key already exists
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        used++;
        head = arr.get(index);
        HashNode<K, V> newNode = new HashNode<K, V>(key, value, hashCode);
        newNode.next = head;
        arr.set(index, newNode);

        Double load = 1.0 * used / space; // * load factor represents the ratio of used to total space
        if (load >= 0.7 && space * 2 <= 1000) { // * if load factor > a threshhold, rehash the table with 2x space
            ArrayList<HashNode<K, V>> temp = arr;
            arr = new ArrayList<>();
            space = 2 * space;
            if (space > 1000) { // ! cap space at 1000 as per lab instructions
                space = 1000;
            }
            used = 0;
            for (int i = 0; i < space; i++) {
                arr.add(null);
            }
            for (HashNode<K, V> node : temp) {
                while (node != null) {
                    add(node.key, node.value);
                    node = node.next;
                }
            }
        }
    }
}