import java.util.ArrayList;

/**
 * This class holds the Graph. It knows of all it's nodes and all their connections.
 * @author Severin Goddon
 */
public class MyGraph {
    private ArrayList<NodeConnection> graphConnections = new ArrayList<>();
    private ArrayList<Node> graphNodes = new ArrayList<>();

    public void addNode(Node node){
        graphNodes.add(node);
    }

    public Node findNode(int id){
        for (Node n: graphNodes) {
            if(n.getId()==id) return n;
        }
        return null;
    }

    public void addConnection(NodeConnection connection){
        graphConnections.add(connection);
    }

    public ArrayList<Node> getConnectedNodes(Node node){
        ArrayList<Node> connectedNodes = new ArrayList<>();
        for(NodeConnection connection : graphConnections){
            if(connection.getConnection().getValue().getId() == node.getId()){
                connectedNodes.add(connection.getConnection().getKey());
            }
        }
        return connectedNodes;
    }
}
