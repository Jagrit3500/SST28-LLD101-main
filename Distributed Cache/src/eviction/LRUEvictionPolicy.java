package eviction;

import java.util.LinkedList;

public class LRUEvictionPolicy implements IEvictionPolicy {

    private LinkedList<String> list = new LinkedList<>();

    @Override
    public void onGet(String key) {
        list.remove(key);
        list.addFirst(key);
    }

    @Override
    public void onPut(String key) {
        list.remove(key);
        list.addFirst(key);
    }

    @Override
    public String evict() {
        return list.removeLast();
    }
}