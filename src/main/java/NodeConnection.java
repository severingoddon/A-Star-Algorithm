import java.util.AbstractMap;
import java.util.Set;

public class NodeConnection {
    private AbstractMap.SimpleEntry<Node, Node> connection;

    public AbstractMap.SimpleEntry<Node, Node> getConnection() {
        return connection;
    }

    public void setConnection(Node firstNode, Node secondNode) {
        connection = new AbstractMap.SimpleEntry<>(firstNode,secondNode);
    }
}
