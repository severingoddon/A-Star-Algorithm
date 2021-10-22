import java.util.ArrayList;

public class MyGraph {
    private ArrayList<NodeConnection> graphConnections = new ArrayList<>();
    private ArrayList<Node> graphNodes = new ArrayList<>();

    public void addNode(Node node){
        graphNodes.add(node);
    }

    public ArrayList<Node> getAllNodes(){
        return graphNodes;
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
    public void deleteConnection(NodeConnection connection){
        graphConnections.remove(connection);
    }
    public ArrayList<NodeConnection> getAllConnections(){
        return graphConnections;
    }
    public boolean checkIfConnected(NodeConnection connection){
        return graphConnections.contains(connection);
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
