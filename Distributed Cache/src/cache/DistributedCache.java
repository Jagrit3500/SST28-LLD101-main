package cache;

import node.ICacheNode;
import distributor.IDistributor;
import database.IDatabase;

import java.util.List;

public class DistributedCache implements ICache {

    private List<ICacheNode> nodes;
    private IDistributor distributor;
    private IDatabase database;

    public DistributedCache(List<ICacheNode> nodes,
                            IDistributor distributor,
                            IDatabase database) {
        this.nodes = nodes;
        this.distributor = distributor;
        this.database = database;
    }

    @Override
    public String get(String key) {
        ICacheNode node = distributor.getNode(key, nodes);
        String value = node.get(key);

        if (value == null) {
            value = database.get(key);
            if (value != null) {
                node.put(key, value);
            }
        }
        return value;
    }

    @Override
    public void put(String key, String value) {
        ICacheNode node = distributor.getNode(key, nodes);
        node.put(key, value);
        database.put(key, value);
    }
}