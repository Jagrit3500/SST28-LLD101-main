package distributor;

import node.ICacheNode;
import java.util.List;

public interface IDistributor {
    ICacheNode getNode(String key, List<ICacheNode> nodes);
}