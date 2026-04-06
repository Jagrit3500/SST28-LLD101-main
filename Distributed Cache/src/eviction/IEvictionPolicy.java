package eviction;

public interface IEvictionPolicy {
    void onGet(String key);
    void onPut(String key);
    String evict();
}