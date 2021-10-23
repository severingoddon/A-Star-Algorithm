import java.util.AbstractMap;
import java.util.Set;


/**
 * This class represents a connection between two nodes. It does that using a pair of two node objects and putting them together
 * in a AbstractMap.SimpleEntry
 * @author Severin Goddon
 */
public class NodeConnection {
    private AbstractMap.SimpleEntry<Node, Node> connection;

    public AbstractMap.SimpleEntry<Node, Node> getConnection() {
        return connection;
    }

    public void setConnection(Node firstNode, Node secondNode) {
        connection = new AbstractMap.SimpleEntry<>(firstNode,secondNode);
    }
}
