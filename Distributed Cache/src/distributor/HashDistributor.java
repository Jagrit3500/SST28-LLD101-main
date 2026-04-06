package distributor;

import node.ICacheNode;
import java.util.List;

public class HashDistributor implements IDistributor {

    @Override
    public ICacheNode getNode(String key, List<ICacheNode> nodes) {
        int index = Math.abs(key.hashCode()) % nodes.size();
        return nodes.get(index);
    }
}