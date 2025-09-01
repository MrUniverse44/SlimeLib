// Copyright (C) 2025 BlueSlime Development
package me.blueslime.slimelib.impls;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;

public class ImplementFactory<T, V extends T> implements Implementer {
    private final V[] values;

    private final String identifier;
    private final boolean persist;

    public ImplementFactory(V[] values) {
        this(false, null, values);
    }

    public ImplementFactory(boolean persist, V[] values) {
        this(persist, null, values);
    }

    public ImplementFactory(boolean persist, String identifier, V[] values) {
        this.values = values;
        this.persist = persist;
        this.identifier = identifier;
    }

    /**
     * @return registered set in the Implements
     */
    public Set<T> asSet() {
        Set<T> set = new HashSet<>(Arrays.asList(values));
        if (identifier != null) {
            registerImpl(Set.class, identifier, set, persist);
        } else {
            registerImpl(Set.class, set, persist);
        }
        return set;
    }

    /**
     * @return registered list in the Implements
     */
    public List<T> asList() {
        return asList(new CopyOnWriteArrayList<>());
    }

    /**
     * @return registered list in the Implements
     * @param list by default, it uses ArrayList, but you can change it here.
     */
    public List<T> asList(List<T> list) {
        list.addAll(Arrays.asList(values));
        if (identifier != null) {
            registerImpl(List.class, identifier, list, persist);
        } else {
            registerImpl(List.class, list, persist);
        }
        return list;
    }

    /**
     * @return registered collection in the Implements
     */
    public Collection<T> asCollection() {
        Collection<T> list = Arrays.asList(values);
        if (identifier != null) {
            registerImpl(Collection.class, identifier, list, persist);
        } else {
            registerImpl(Collection.class, list, persist);
        }
        return list;
    }

    /**
     * @return registered map in the Implements
     * @param consumer in this consumer you need to register your value to the map.
     */
    public <K> Map<K, V> asMap(BiConsumer<V, Map<K, V>> consumer) {
        return asMap(consumer, new HashMap<>());
    }

    /**
     * @return registered map in the Implements
     * @param consumer in this consumer you need to register your value to the map.
     * @param map by default, it uses HashMap, but you can create another type of map.
     */
    public <K> Map<K, V> asMap(BiConsumer<V, Map<K, V>> consumer, Map<K, V> map) {
        for (V value : values) {
            consumer.accept(value, map);
        }
        if (identifier != null) {
            registerImpl(Map.class, identifier, map, persist);
        } else {
            registerImpl(Map.class, map, persist);
        }
        return map;
    }
}
