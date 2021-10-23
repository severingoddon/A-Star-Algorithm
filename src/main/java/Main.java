import java.util.ArrayList;
import java.util.Random;

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
    private static ArrayList<Node> alreadyVisitedNodes = new ArrayList<>();
    private static String[][] basicGrid;
    private static ArrayList<Node> connectedNodes = new ArrayList<>();

    private static ArrayList<Node> openNodes = new ArrayList<>(); //used for a new approach, not in use yet
    private static ArrayList<Node> closedNodes = new ArrayList<>();  //used for a new approach, not in use yet

    public static void main(String[] args) {

        //create start and endnodes and add them to the graph
        startNode = new Node();
        startNode.setId(25);
        endNode = new Node();
        endNode.setId(9);
        graph.addNode(startNode);
        graph.addNode(endNode);

        //adjacency matrix
        int[][] graphArray =
                {   //    0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27
                         {0,1,0,0,0,0,0,1,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //0
                         {1,0,1,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //1
                         {0,1,0,1,0,0,0,0,1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //2
                         {0,0,1,0,1,0,0,0,0,1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //3
                         {0,0,0,1,0,1,0,0,0,0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //4
                         {0,0,0,0,1,0,1,0,0,0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //5
                         {0,0,0,0,0,1,0,0,0,0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //6
                         {1,0,0,0,0,0,0,0,0,0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //7
                         {0,0,1,0,0,0,0,0,0,1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //8
                         {0,0,0,1,0,0,0,0,1,0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //9
                         {0,0,0,0,1,0,0,0,0,1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //10
                         {0,0,0,0,0,1,0,0,0,0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //11
                         {0,0,0,0,0,0,1,0,0,0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //12
                         {0,0,0,0,0,0,0,1,0,0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //13
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0}, //14
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, //15
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, //16
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0}, //17
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0}, //18
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0}, //19
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, //20
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, //21
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0}, //22
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0}, //23
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0}, //24
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0}, //25
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1}, //26
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0}, //27
                        //0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27
        };

        //this is just the graph represented as a 2D array. I use this to calculate the distances between the nodes
        basicGrid = new String[][]{
                {"0",  "1",  "2",  "3",  "4",  "5",  "6"},
                {"7",  "x",  "8",  "9",  "10", "11", "12"},
                {"13", "x",  "x",  "x",  "x",  "x",  "14"},
                {"15", "16", "17", "18", "19", "x",  "20"},
                {"21", "22", "23", "24", "25", "26", "27"},
        };


        //add all nodes to the graph and calculate their costs
        for(int i = 0; i< graphArray.length; i++){
            if(i!=9 && i!=25) { //start and end node have already been added so skip these two nodes with index 9 and 25
                Node node = new Node();
                node.setId(i);
                double gCost = calculateDistanceBetweenNodes(node, startNode);
                double hCost = calculateDistanceBetweenNodes(node, endNode);
                node.setgCost(gCost);
                node.sethCost(hCost);
                node.setfCost(gCost + hCost);
                graph.addNode(node);
            }
        }

        //add all connectons in the graph
        addConnections(graphArray);

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

    public static void calculateShortestPath(){
        Node startNode = graph.findNode(25);
        Node endNode = graph.findNode(9);
        Node currentNode = startNode;

        //mainloop
        while(!currentNode.equals(endNode)){
            System.out.println(currentNode.getId());
            System.out.println(currentNode.getfCost());
            System.out.println(" ");
            alreadyVisitedNodes.add(currentNode);
            currentNode = findNextNode(currentNode);
        }
        System.out.println(currentNode.getId());
    }

    /*
    This Method is in development and is a new try. It's not in use yet
     */
    public static void calculateShortestPathNew(){
        Node currentNode = startNode;
        Node endNode = graph.findNode(9);
        openNodes.add(currentNode);

        while (!(currentNode.equals(endNode))){
            ArrayList<Node> neighbours;
            neighbours = graph.getConnectedNodes(currentNode);
            for (Node neighbour:neighbours) {
                
            }
        }
    }

    public static Node findNextNode(Node currentNode){
        Node nextNode = null;
        while (true) {
            ArrayList<Node> newConnectedNodes;
            newConnectedNodes = graph.getConnectedNodes(currentNode);
            for (Node newNode : newConnectedNodes) {
                if (!connectedNodes.contains(newNode)) {
                    connectedNodes.add(newNode);
                }
            }
            while (true) {
                Random rn = new Random();
                nextNode = connectedNodes.get(rn.nextInt(connectedNodes.size()));
                if (!(nextNode.equals(currentNode))) break;
            }

            for (Node node : connectedNodes) {
                if (node.getfCost() < nextNode.getfCost() && !(alreadyVisitedNodes.contains(node))) {
                    nextNode = node;
                }
            }
            if(!(alreadyVisitedNodes.contains(nextNode)))break;
        }
        return nextNode;
    }

    /*
    This Method is in development and is a new try. It's not in use yet
     */
    public static Node findNextNodeNew(Node currentNode){
        return null;
    }

    /*
    This Method calculates the distance between two nodes in the graph. It uses the diagonal distance as metric (pythagoras)
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
        return(Math.sqrt((differenceBetweenColumns*differenceBetweenColumns+differenceBetweenRows*differenceBetweenRows))*10);
    }
}
