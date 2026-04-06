package node;

import eviction.IEvictionPolicy;

import java.util.HashMap;
import java.util.Map;

public class CacheNode implements ICacheNode {

    private Map<String, String> storage;
    private IEvictionPolicy evictionPolicy;
    private int capacity;

    public CacheNode(int capacity, IEvictionPolicy evictionPolicy) {
        this.capacity = capacity;
        this.evictionPolicy = evictionPolicy;
        this.storage = new HashMap<>();
    }

    @Override
    public String get(String key) {
        if (!storage.containsKey(key)) return null;

        evictionPolicy.onGet(key);
        return storage.get(key);
    }

    @Override
    public void put(String key, String value) {
        if (!storage.containsKey(key) && storage.size() >= capacity) {
            String evictKey = evictionPolicy.evict();
            storage.remove(evictKey);
        }

        storage.put(key, value);
        evictionPolicy.onPut(key);
    }
}