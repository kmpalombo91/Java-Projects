package mp7;

import asdv.ListASDV;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;//needed in putAll

public class MapASDV<K, V> implements Map<K, V>, Cloneable {

    private int capacity = 4;
    private double loadFactor = 0.75;
    private ArrayList<ListASDV<EntryASDV<K, V>>> map = new ArrayList<ListASDV<EntryASDV<K, V>>>();
    private int size = 0;
    private Set<K> sharedKeySet = new SharedSet<K>();
    private Set<Entry<K, V>> sharedEntrySet = new SharedSet<Entry<K, V>>();
    private Collection<V> sharedValuesCollection = new SharedCollection<V>();

    private class SharedCollection<E> extends ArrayList<E> {
        Collection<V> collection;

        public SharedCollection() {
            collection = new ArrayList<>();
        }

        public Collection<V> getCollection() {
            return collection;
        }

        public void setCollection(Collection<V> collection) {
            this.collection = collection;
        }
        
        @Override
        public boolean remove(Object o) {
            EntryASDV<K, V> e;
            int i = 0;
            for (ListASDV<EntryASDV<K, V>> list : map) {
                for (EntryASDV<K, V> entry : list) {
                    if (entry.getValue().equals(o) && i == 0) {
                        e = (EntryASDV<K, V>) entry;
                        list.remove(e);
                        sharedKeySet.remove(e.getKey());
                        sharedEntrySet.remove(e);
                        i++;
                    }
                }
            }
            return super.remove(o);
        }
        
        public boolean addValue(V v) {
            return super.add((E) v);
        }
        
        @Override
        public boolean add(E e) {
            throw new UnsupportedOperationException("Not supported....");
        }
    }

    private class SharedSet<E> extends HashSet<E> {
        Set<E> set;
        
        public SharedSet() {
            set = new LinkedHashSet<>();
        }

        public Set<E> getSet() {
            return set;
        }

        public void setSet(Set<E> set) {
            this.set = set;
        }
        
        @Override
        public boolean remove(Object o) {
            EntryASDV<K, V> e;
            for (ListASDV<EntryASDV<K, V>> list : map) {
                for (EntryASDV<K, V> entry : list) {
                    if (entry.getKey().equals(o)) {
                        e = (EntryASDV<K, V>) entry;
                        list.remove(e);
                        sharedValuesCollection.remove(e.getValue());
                        sharedEntrySet.remove(e);
                    } else if (entry.equals(o)) {
                        e = (EntryASDV<K, V>) entry;
                        list.remove(e);
                        sharedValuesCollection.remove(e.getValue());
                        sharedKeySet.remove(e.getKey());
                    }
                }
            }
            return super.remove(o);
        }
        
        @Override
        public boolean add(E e) {
            throw new UnsupportedOperationException("Not supported....");
        }
        
        public boolean addEntry(EntryASDV<K, V> e) {
            return super.add((E) e);
        }
        
        public boolean addEntry(K k) {
            return super.add((E) k);
        }
    }

        public MapASDV() {
            for (int i = 0; i < capacity; ++i) {
                map.add(new ListASDV<EntryASDV<K, V>>());
            }
        }

        /**
         * Double the size of the Map and rehashes the entries of the Map
         */
        private void doubleTheSizeOfTheMapAndRehash() {
            capacity *= 2;
            ArrayList<ListASDV<EntryASDV<K, V>>> temp = map;
            map = new ArrayList<>();
            int dif = capacity - temp.size();
            for (int i = 0; i < capacity; ++i) {
                map.add(new ListASDV<EntryASDV<K, V>>());
            }
            for (int i = 0; i < temp.size(); ++i) {
                ListASDV<EntryASDV<K, V>> list = temp.get(i);
                for (int j = 0; j < list.size(); ++j) {
                    int index = hash(list.get(j).getKey().hashCode());
                    map.get(index).add(list.get(j));
                }
            }
        }

        class EntryASDV<K, V> implements Entry<K, V>, Comparable<K> {

            K key;
            V value;

            public EntryASDV(K key, V value) {
                this.key = key;
                this.value = value;
            }

            @Override
            public K getKey() {
                return key;
            }

            @Override
            public V getValue() {
                return value;
            }

            @Override
            public V setValue(V value) {
                V oldValue = this.value;
                this.value = value;
                return oldValue;
            }

            @Override
            public String toString() {
                return "EntryASDV{" + "key=" + key + ", value=" + value + '}';
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null) {
                    return false;
                }
                if (getClass() != obj.getClass()) {
                    return false;
                }
                final EntryASDV<?, ?> other = (EntryASDV<?, ?>) obj;
                if (!Objects.equals(this.key, other.key)) {
                    return false;
                }
                return true;
            }

            /**
             *
             * @param o
             * @return throws IllegalArgumentException if parameter class is not
             * K
             */
            @Override
            public int compareTo(K o) {
                if (getClass() != o.getClass()) {
                    throw new IllegalArgumentException("ellegal parameter " + o);
                }
                return ((Comparable) key).compareTo(o);
            }

        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public boolean isEmpty() {
            return size() == 0;
        }

        @Override
        public boolean containsKey(Object key) {
            for (int i = 0; i < capacity; i++) {
                if (map.get(i) != null) {
                    ListASDV<EntryASDV<K, V>> temp = map.get(i);
                    for (EntryASDV<K, V> e : temp) {
                        if (e.getKey().equals(key)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        /**
         * Return an entry of the map
         *
         * @param key the key of the entry to be returned
         * @return the entry, or null if the key is not in the map
         */
        EntryASDV<K, V> getEntryForKey(Object key) {
            for (ListASDV<EntryASDV<K, V>> list : map) {
                Iterator<EntryASDV<K, V>> it = list.iterator();
                while (it.hasNext()) {
                    EntryASDV<K, V> entry = it.next();
                    if (key.equals(entry.getKey())) {
                        return entry;
                    }
                }
            }
            return null;
        }

        /**
         * Returns the index of the given key
         *
         * @param key a key of the map
         * @return the index of a key in the map or -1, if the key is not in the
         * map
         */
        int getIndexForKey(Object key) {
            for (int i = 0; i < map.size(); ++i) {
                Iterator<EntryASDV<K, V>> it = map.get(i).iterator();
                while (it.hasNext()) {
                    EntryASDV<K, V> entry = it.next();
                    if (key.equals(entry.getKey())) {
                        return i;
                    }
                }
            }
            return -1;
        }

        /**
         * Returns true if this map maps one or more keys to the specified
         * value. More formally, returns true if and only if this map contains
         * at least one mapping to a value v such that (value==null ? v==null :
         * value.equals(v)). This operation will probably require time linear in
         * the map size for most implementations of the Map interface.
         *
         * Parameters: value - value whose presence in this map is to be tested
         * Returns: true if this map maps one or more keys to the specified
         * value Throws: ClassCastException - if the value is of an
         * inappropriate type for this map (optional) NullPointerException - if
         * the specified value is null and this map does not permit null values
         * (optional)
         *
         * @param value - value whose presence in this map is to be tested
         * @return true if this map maps one or more keys to the specified value
         * @throws NullPointerException - if the specified value is null and
         * this map does not permit null values (optional)
         */
        @Override
        public boolean containsValue(Object value) {
            for (int i = 0; i < capacity; i++) {
                if (map.get(i) != null) {
                    ListASDV<EntryASDV<K, V>> temp = map.get(i);
                    for (EntryASDV<K, V> e : temp) {
                        if (e.getValue().equals(value)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        /**
         * Returns the value to which the specified key is mapped, or null if
         * this map contains no mapping for the key.
         *
         * More formally, if this map contains a mapping from a key k to a value
         * v such that (key==null ? k==null : key.equals(k)), then this method
         * returns v; otherwise it returns null. (There can be at most one such
         * mapping.)
         *
         * If this map permits null values, then a return value of null does not
         * necessarily indicate that the map contains no mapping for the key;
         * it's also possible that the map explicitly maps the key to null. The
         * containsKey operation may be used to distinguish these two cases.
         *
         * @param key - the key whose associated value is to be returned
         * @return the value to which the specified key is mapped, or null if
         * this map contains no mapping for the key
         * @throws NullPointerException - if the specified key is null and this
         * map does not permit null keys (optional)
         */
        @Override
        public V get(Object key) {
            if (key == null) {
                throw new NullPointerException("null parameter");
            }
            for (int i = 0; i < capacity; i++) {
                if (map.get(i) != null) {
                    ListASDV<EntryASDV<K, V>> temp = map.get(i);
                    for (EntryASDV<K, V> e : temp) {
                        if (e.getKey().equals(key)) {
                            return (V) e.getValue();
                        }
                    }
                }
            }
            return null;
        }

        /**
         * Associates the specified value with the specified key in this map
         * (optional operation). If the map previously contained a mapping for
         * the key, the old value is replaced by the specified value. (A map m
         * is said to contain a mapping for a key k if and only if
         * m.containsKey(k) would return true.)
         *
         * @param key - key with which the specified value is to be associated
         * @param value - value to be associated with the specified key
         * @return the previous value associated with key, or null if there was
         * no mapping for key. (A null return can also indicate that the map
         * previously associated null with key, if the implementation supports
         * null values.)
         * @throws NullPointerException - if specified key or value is null and
         * this map does not permit null keys
         */
        @Override
        public V put(K key, V value
        ) {
            if (key == null || value == null) {
                throw new NullPointerException("null parameter");
            }
            EntryASDV<K, V> entry = getEntryForKey(key);
            if (entry != null) {
                V old = entry.value;
                entry.value = value;
                return old;
            }
            int code = this.hash(key.hashCode());
            int i = hash(code);

            ListASDV<EntryASDV<K, V>> list = map.get(i);
            EntryASDV e = new EntryASDV(key, value);
            list.add(e);
            size++;

            //>>add the key to the shared keys-set
            ((SharedSet<K>) this.sharedKeySet).addEntry(key);
            ((SharedSet<Entry<K, V>>) this.sharedEntrySet).addEntry(e);

//>>get the value of this entry
            V v = list.get(list.size() - 1).getValue();
//>> add value to the value collection
            ((SharedCollection<V>) this.sharedValuesCollection).addValue(v);
//>> if reach 75% capacity double the size
            if ((double) this.size() / capacity >= 0.75) {
                this.doubleTheSizeOfTheMapAndRehash();
            }
//>>return the value of Entry just added
            return value;
        }

        int hash(int keyHashCode
        ) {
            int h = hashHash(keyHashCode);
            return Math.abs(h % capacity - 1);
        }

        /**
         * Removes the first entry with the given values.
         *
         * @param value - the value to be removed
         * @return true if removed, false otherwise
         * @throws NullPointerException if the value is null
         */
        private boolean removeFirstValue(Object value) {
            if (value == null) {
                throw new NullPointerException("null parameter");
            }
            int i = 0;
            for (ListASDV<EntryASDV<K, V>> list : map) {
                for (EntryASDV<K, V> entry : list) {
                    if (entry.getValue().equals(value) && i == 0) {
                        list.remove(entry);
                        i++;
                        return true;
                    }
                }
            }
            return false;
        }

        /**
         * Ensure hash code is evenly distributed
         *
         * @param h - hash code
         * @return evenly distributed hash code
         */
        private static int hashHash(int h) {
            h ^= (h >>> 20) ^ (h >>> 12);
            return h ^ (h >>> 7) ^ (h >>> 4);
        }

        /**
         * Removes the mapping for a key from this map if it is present
         * (optional operation). More formally, if this map contains a mapping
         * from key k to value v such that (key==null ? k==null :
         * key.equals(k)), that mapping is removed. (The map can contain at most
         * one such mapping.)
         *
         * @param key - key whose mapping is to be removed from the map
         * @return the previous value associated with key, or null if there was
         * no mapping for key.
         * @throws NullPointerException - if the specified key is null and this
         * map does not permit null keys
         */
        @Override
        public V remove(Object key) {
            int i = hash(key.hashCode());
            if (map.get(i) != null) {
                ListASDV<EntryASDV<K, V>> temp = map.get(i);
                for (EntryASDV<K, V> e : temp) {
                    if (e.getKey().equals(key)) {
                        temp.remove(e);
                        sharedValuesCollection.remove(e.getValue());
                        sharedKeySet.remove(key);
                        sharedEntrySet.remove(e);
                    }
                    return e.getValue();
                }
                size--;
            }
            return null;
        }

        /**
         * Copies all of the mappings from the specified map to this map
         * (optional operation). The effect of this call is equivalent to that
         * of calling put(k, v) on this map once for each mapping from key k to
         * value v in the specified map. The behavior of this operation is
         * undefined if the specified map is modified while the operation is in
         * progress.
         *
         * @param m - mappings to be stored in this map
         * @throws NullPointerException - if the specified map is null, or if
         * this map does not permit null keys or values, and the specified map
         * contains null keys
         */
        @Override
        public void putAll(Map<? extends K, ? extends V> m) {
            for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
                this.put(entry.getKey(), entry.getValue());
            }
        }

        /**
         * Removes all of the mappings from this map (optional operation). The
         * map will be empty after this call returns. Any shared sets are also
         * cleared.
         */
        @Override
        public void clear() {
            map = new ArrayList<ListASDV<EntryASDV<K, V>>>();
            for (int i = 0; i < capacity; ++i) {
                map.add(new ListASDV<EntryASDV<K, V>>());
            }
            sharedKeySet.clear();
            sharedEntrySet.clear();
            size = 0;
        }

        /**
         * Returns a Set view of the keys contained in this map. The set is
         * backed by the map, so changes to the map are reflected in the set,
         * and vice-versa. If the map is modified while an iteration over the
         * set is in progress (except through the iterator's own remove
         * operation), the results of the iteration are undefined. The set
         * supports element removal, which removes the corresponding mapping
         * from the map, via the Iterator.remove, Set.remove, removeAll,
         * retainAll, and clear operations. It does not support the add or
         * addAll operations.
         *
         * @return a set view of the keys contained in this map
         */
        @Override
        public Set<K> keySet() {
            return this.sharedKeySet;
        }

        /**
         * Returns a Collection view of the values contained in this map. The
         * collection is backed by the map, so changes to the map are reflected
         * in the collection, and vice-versa. If the map is modified while an
         * iteration over the collection is in progress (except through the
         * iterator's own remove operation), the results of the iteration are
         * undefined. The collection supports element removal, which removes the
         * corresponding mapping from the map, via the Iterator.remove,
         * Collection.remove, removeAll, retainAll and clear operations. It does
         * not support the add or addAll operations.
         *
         * @return
         */
        @Override
        public Collection<V> values() {
            return sharedValuesCollection;
        }

        /**
         * Returns a Set view of the mappings contained in this map. The set is
         * backed by the map, so changes to the map are reflected in the set,
         * and vice-versa. If the map is modified while an iteration over the
         * set is in progress (except through the iterator's own remove
         * operation, or through the setValue operation on a map entry returned
         * by the iterator) the results of the iteration are undefined. The set
         * supports element removal, which removes the corresponding mapping
         * from the map, via the Iterator.remove, Set.remove, removeAll,
         * retainAll and clear operations. It does not support the add or addAll
         * operations.
         *
         *
         * @return a set view of the mappings contained in this map
         */
        @Override
        public Set<Entry<K, V>> entrySet() {
            return this.sharedEntrySet;
        }

        @Override
        public String toString() {
            String s = "[ ";
            for (int i = 0; i < capacity; ++i) {
                s += map.get(i).toString() + "\n";
            }
            s += " ]";

            return s;
        }

        /**
         * Created a deep copy of the MapASDV
         *
         * @return the deep copy of the map
         */
        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();

        }

        public static void main(String[] args) throws CloneNotSupportedException {

            MapASDV<String, Integer> map = new MapASDV();
            System.out.println("---------------------------testing put(K, V)");
            map.put("ann", 20);
            map.put("coco", 25);
            System.out.println(map);
            MapASDV<String, Integer> clonedMap = (MapASDV<String, Integer>) map.clone();

            System.out.println("\n\n---------------------------testing double-the-size-and-rehash by reaching 0.75 load factor with another put(K, V");
            map.put("Jonathan", 30);
            System.out.println(map);
            map.put("Jonhathan", 45);
            System.out.println(map);
            map.put("Alexander", 33);
            System.out.println(map);

            System.out.println("\n\n---------------------------testing putAll(Map<K,V>");
            Map<String, Integer> anotherJavaMap = new HashMap();
            anotherJavaMap.put("lion king", 45);
            anotherJavaMap.put("HYENA", 6);
            map.putAll(anotherJavaMap);
            System.out.println(map);
            System.out.println("\n\n---------------------------testing containsKey");
            System.out.println(map.containsKey("Alexander"));
            System.out.println(map.containsKey("alexander"));

            System.out.println("\n\n---------------------------testing containsValue");
            System.out.println(map.containsValue(33));
            System.out.println(map.containsValue(34));

            System.out.println("\n\n---------------------------testing getEntryForKey");
            Entry<String, Integer> e = map.getEntryForKey("Alexander");
            System.out.println(map.getEntryForKey("Alexander"));
            System.out.println(map.getEntryForKey("Alex"));

            System.out.println("\n\n---------------------------testing get");
            System.out.println(map.get("Alexander"));
            System.out.println(map.get("Alex"));
            try {
                map.get(null);
            } catch (NullPointerException ex) {
                System.out.println(ex.getMessage());
            }

            System.out.println("\n\n---------------------------testing getIndexForKey");
            System.out.println(map.getIndexForKey("Alexander"));
            System.out.println(map.getIndexForKey("Alex"));

            System.out.println("\n\n---------------------------testing isEmpty");
            System.out.println(map.isEmpty());

            System.out.println("\n\n---------------------------testing size");
            System.out.println(map.size());
            System.out.println(map);

            System.out.println("\n\n---------------------------testing entrySet()");
            Set<Entry<String, Integer>> entries = map.entrySet();
            System.out.println(entries);

            System.out.println("\n\n---------------------------testing keySet()");
            Set<String> keys = map.keySet();
            System.out.println(keys);

            System.out.println("\n\n---------------------------testing values()");
            Collection<Integer> values = map.values();
            System.out.println(values);

            System.out.println("\n\n---------------------------testing remove( K) coco 25");
            map.remove("coco");
            System.out.println(map);
            System.out.println(entries);
            System.out.println(keys);
            System.out.println(values);

            System.out.println("\n\n---------------------------testing Entry-Collection remove  )");
        entries.remove(e);
        System.out.println(map);
        System.out.println(entries);
        System.out.println(keys);
        System.out.println(values);

        System.out.println("\n\n---------------------------testing Set Keys remove  )");
        keys.remove("ann");
        System.out.println(map);
        System.out.println(entries);
        System.out.println(keys);
        System.out.println(values);

        System.out.println("\n\n---------------------------testing Set Values remove  )");
        values.remove(45);
        System.out.println(map);
        System.out.println(entries);
        System.out.println(keys);
        System.out.println(values);
            System.out.println("\n\n---------------------------testing clear  )");
            map.clear();
            System.out.println(map);
            System.out.println(entries);
            System.out.println(keys);

            System.out.println("\n\n---------------------------testing add of sets and collections  )");
        try {
            keys.add("a");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        try {
            values.add(33);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        try {
            entries.add(e);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("\n\n---------------------------testing clone");
        System.out.println(clonedMap);

        System.out.println("---------------------------testing put(K, V) AGAIN");
        map.put("Nicholas", 100);
        map.put("a", 200);
        map.put("b", -20);
        System.out.println(map);
        }
    }
