package database;

public interface IDatabase {
    String get(String key);
    void put(String key, String value);
}