package app;

import cache.*;
import node.*;
import eviction.*;
import distributor.*;
import database.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        int numNodes = 3;
        int capacity = 2;

        List<ICacheNode> nodes = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            nodes.add(new CacheNode(capacity, new LRUEvictionPolicy()));
        }

        IDistributor distributor = new HashDistributor();
        IDatabase db = new InMemoryDatabase();

        ICache cache = new DistributedCache(nodes, distributor, db);

        cache.put("a", "1");
        cache.put("b", "2");

        System.out.println(cache.get("a")); // 1
        System.out.println(cache.get("b")); // 2
        System.out.println(cache.get("c")); // null

        // Trigger LRU eviction
        cache.put("c", "3");
        cache.put("d", "4");

        System.out.println(cache.get("a")); // might be null (evicted)
    }
}