package node;

public interface ICacheNode {
    String get(String key);
    void put(String key, String value);
}