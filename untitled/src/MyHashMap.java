public class MyHashMap <K, V> {

    private final int INITIAL_SIZE = 1 << 4;
    private final int MAX_CAPACITY = 1 << 30;

    private Entry[] hashTable;

    public MyHashMap() {
        hashTable = new Entry[INITIAL_SIZE];
    }

    public MyHashMap(int capacity) {
        var tableSize = findTableCapacity(capacity);
        hashTable = new Entry[tableSize];
    }

    private int findTableCapacity(int capacity) {
        var n = capacity - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;

        return n <= 0 ? 1 : n >= MAX_CAPACITY ? MAX_CAPACITY : n + 1;
    }

    public class Entry<K, V> {
        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value) {
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

    public void put(K key, V value) {
        var hashCode = key.hashCode() % hashTable.length;
        var node = hashTable[hashCode];

        if (node == null) {
            hashTable[hashCode] = new Entry(key, value);
        } else {
            var previousNode = node;
            while (node != null) {
                if (node.key == key) {
                    node.value = value;
                    return;
                }

                previousNode = node;
                node = node.next;
            }
        }
    }

    public V get(K key) {
        var hashCode = key.hashCode() % hashTable.length;
        var node = hashTable[hashCode];

        while (node != null) {
            if (node.key == key) {
                return (V) node.value;
            }

            node = node.next;
        }

        return null;
    }
}
