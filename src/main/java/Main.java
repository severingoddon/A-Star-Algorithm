import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class contains the A* Algorithm and does all it's steps. It uses the Graph class and the Node class.
 * The first part of the class is some initializing stuff like the adjacency matrix and or start and endnodes.
 * The second part of the class is the algorithm itself and some helper methods like calculating the distance between two nodes.
 * @author Severin Goddon
 */
public class Main {

    private static MyGraph graph = new MyGraph();
    private static Node startNode;//this is the start node of path
    private static Node endNode; //this is the end node of path
    private static String[][] basicGrid;
    private static ArrayList<Node> openNodes = new ArrayList<>(); //used for a new approach, not in use yet
    private static ArrayList<Node> closedNodes = new ArrayList<>();  //used for a new approach, not in use yet
    private static GraphSource graphSource = new GraphSource();

    public static void main(String[] args) {

        basicGrid = graphSource.getBasicGrid();
        int[][] graphArray = graphSource.getGeneratedAdjacencyMatrix();

        //create start and endnodes and add them to the graph
        startNode = new Node();
        startNode.setId(graphSource.getStartnode());
        startNode.setgCost(0);
        endNode = new Node();
        endNode.setId(graphSource.getEndnode());
        double hcost = calculateDistanceBetweenNodes(startNode,endNode);
        startNode.sethCost(hcost);
        startNode.setfCost(hcost);
        graph.addNode(startNode);
        graph.addNode(endNode);

        //add all nodes to the graph and calculate their costs
        for(int i = 0; i< graphArray.length; i++){
            if(i!=startNode.getId() && i!=endNode.getId()) { //start and end node have already been added so skip these two nodes
                Node node = new Node();
                node.setId(i);
                double hCost = calculateDistanceBetweenNodes(node, endNode);
                node.sethCost(hCost);
                graph.addNode(node);
            }
        }

        //add all connectons in the graph
        addConnections(graphArray);

        //do the algorithm
        calculateShortestPath();
    }

    /*
    This method adds the connections between the nodes
     */
    public static void addConnections(int[][] graphArray){
        for(int i = 0; i<graphArray.length; i++){
            for(int j = 0; j<graphArray.length; j++){
                if(graphArray[j][i]==1){
                    NodeConnection connection = new NodeConnection();
                    connection.setConnection(graph.findNode(i), graph.findNode(j));
                    graph.addConnection(connection);
                }
            }
        }
    }


    /*
    This Method is the algorithm and does everything. It's long but it works very fine
     */
    public static void calculateShortestPath(){
        Node currentNode = startNode;
        Node endNode = graph.findNode(graphSource.getEndnode());
        openNodes.add(currentNode);

        while(!(currentNode.equals(endNode))) {

            //calculate current fcosts in open nodes
            if (!(closedNodes.size() == 0)) {
                ArrayList<Node> newOpenNodes = graph.getConnectedNodes(currentNode);
                newOpenNodes.removeIf(n -> closedNodes.contains(n));
                for(Node newOpenNode:newOpenNodes){
                    if(!(openNodes.contains(newOpenNode)&& !(closedNodes.contains(newOpenNode)))){
                        openNodes.add(newOpenNode);
                    }
                }

                for (Node opennode : openNodes) {
                    ArrayList<Node> connectedNodesOfOpenNode = graph.getConnectedNodes(opennode);
                    for (Node connectedNode : connectedNodesOfOpenNode) {
                        if (closedNodes.contains(connectedNode)) {
                            if (opennode.getParentNode() != null) {
                                if (connectedNode.getfCost() < opennode.getParentNode().getfCost()) {
                                    opennode.setParentNode(connectedNode);
                                    opennode.setgCost(connectedNode.getgCost() + 10);
                                    opennode.setfCost(opennode.getgCost() + opennode.gethCost());
                                }
                            } else {
                                opennode.setParentNode(connectedNode);
                                opennode.setgCost(connectedNode.getgCost() + 10);
                                opennode.setfCost(opennode.getgCost() + opennode.gethCost());
                            }
                        }
                    }

                }
            } else {
                closedNodes.add(currentNode);
            }

            double fcost = 10000000;

            //loop trough the open nodes and find the lowest fcost
            for (Node openNode:openNodes) {
                if(openNode.getfCost()<fcost){
                    fcost = openNode.getfCost();
                }
            }
            ArrayList<Node> nodesWithLowestFcost = new ArrayList<>();
            //find all open nodes with the lowest fcost and add them to the list
            for(Node openNode:openNodes){
                if(openNode.getfCost()==fcost){
                    nodesWithLowestFcost.add(openNode);
                }
            }

            double hcost = 1000000;
            //choose the node with the lowest hcost out of the list of nodes with lowest fcosts
            for (Node node:nodesWithLowestFcost) {
                if(node.gethCost()<hcost){
                    hcost = node.gethCost();
                    currentNode = node;
                }
            }
            openNodes.remove(currentNode);
            closedNodes.add(currentNode);
            System.out.println("Id of current node: "+currentNode.getId());
            System.out.println("F-Cost of current node: "+currentNode.getfCost());
            System.out.println("G-Cost of current node: "+currentNode.getgCost());
            System.out.println("H-Cost of current node: "+currentNode.gethCost());
            System.out.println("");

        }

        //go trough the nodes and their parent nodes to determine the final shortest path
        ArrayList<Integer> finalPath = new ArrayList<>();
        Node nextnode = currentNode;
        while (true){
            finalPath.add(nextnode.getId());
            if(nextnode.getParentNode()!=null) {
                nextnode = nextnode.getParentNode();
            }
            else{
                break;
            }
        }
        int[] path = new int[finalPath.size()];

        int j = 0;
        for(int i = finalPath.size()-1; i>=0; i--){
            path[j] = finalPath.get(i);
            j++;
        }

        //print out the final path
        System.out.println("");
        System.out.println("");
        System.out.println("******************************************");
        System.out.println("***   final path is:  *** ");
        System.out.println("***  "+ Arrays.toString(path) +"  ***");
        System.out.println("******************************************");

    }


    /*
    This Method calculates the distance between two nodes in the graph.
     */
    public static double calculateDistanceBetweenNodes(Node firstNode, Node secondNode){
        String idOfFirstNode = Integer.toString(firstNode.getId());
        String idOfSecondNode = Integer.toString(secondNode.getId());
        int rowOfFirstNode = 0;
        int columnOfFirstNode = 0;
        int rowOfSecondNode = 0;
        int columnOfSecondNode = 0;
        for(int i = 0; i< basicGrid.length; i++){
            for(int j = 0; j< basicGrid[0].length; j++){
                if(basicGrid[i][j].equals(idOfFirstNode)){
                    rowOfFirstNode = i;
                    columnOfFirstNode = j;
                }
                if(basicGrid[i][j].equals(idOfSecondNode)){
                    rowOfSecondNode = i;
                    columnOfSecondNode = j;
                }
            }
        }
        int differenceBetweenRows = rowOfFirstNode-rowOfSecondNode;
        if(differenceBetweenRows<0) differenceBetweenRows = differenceBetweenRows*-1; //if difference is negative, make it positive
        int differenceBetweenColumns = columnOfFirstNode-columnOfSecondNode;
        if(differenceBetweenColumns<0) differenceBetweenColumns = differenceBetweenColumns*-1;
        return((differenceBetweenColumns+differenceBetweenRows)*10);
    }
}
